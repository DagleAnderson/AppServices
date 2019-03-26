package com.appServices.AppServices.Services;


import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.appServices.AppServices.Service.exception.AuthorizationException;
import com.appServices.AppServices.Service.exception.DataIntegrityException;
import com.appServices.AppServices.Service.exception.ObjectNotFoundException;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.EnderecoCliente;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.enums.TipoPerfil;
import com.appServices.AppServices.domain.enums.TipoPessoa;
import com.appServices.AppServices.domain.enums.TipoSexo;
import com.appServices.AppServices.dto.ClienteDTO;
import com.appServices.AppServices.dto.ClienteNewDTO;
import com.appServices.AppServices.repositories.ClienteRepository;
import com.appServices.AppServices.repositories.EnderecoClienteRepository;
import com.appServices.AppServices.repositories.PrestadorRepository;
import com.appServices.AppServices.security.UserSpringSecurity;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoClienteRepository enderecoRepository;
	
	@Autowired 
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3service s3service;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private PrestadorRepository prestadorRepository;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public Cliente find(Integer id) {
		
		UserSpringSecurity user = UserService.authenticated();
		
		if (user == null || !user.hasRole(TipoPerfil.ADMIN) && !id.equals(user.getId())){
			throw new  AuthorizationException("Acesso negado");
		}
		
		Optional<Cliente> objOp = repository.findById(id);
		
		return objOp.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())
				);
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		
		obj = repository.save(obj);
		
		enderecoRepository.save(obj.getEndereco());
		
		
		return  obj; 
	}
	
	public Cliente update(Cliente obj){	
		Cliente newObj = this.find(obj.getId());
		updateData(newObj,obj);
		return  repository.save(newObj);
			
	}
	
	public void delete(Integer id) {
		this.find(id);
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException( e.getMessage());
		}
	}
	
	public List<Cliente> findAll(){
		return repository.findAll();
	}
	
	public Cliente findByEmail(String email){
		UserSpringSecurity user = UserService.authenticated();
		if(user == null || !user.hasRole(TipoPerfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		Cliente obj = repository.findByEmail(email);
		if(obj ==null) {
			throw new ObjectNotFoundException("Objeto não encontrado! id:"+ user.getId()+",Tipo:"+ Cliente.class.getName());
		}
		
		return obj;
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		
		Cliente cliente = new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getSobrenome(), objDTO.getDataNascimento(),objDTO.getRg(), objDTO.getcpfOuCnpj(),TipoPessoa.toEnum(objDTO.getTipoPessoa()), TipoSexo.toEnum(objDTO.getSexo()),objDTO.getSenha(),objDTO.getEmail());
		
		if(objDTO.getPrestador() != null) {
			Optional<Prestador> prestador = prestadorRepository.findById(objDTO.getPrestador());
			cliente.setPrestador(prestador.get());
			}
		else {
			objDTO.setPrestador(null);
		}
		
		cliente.getTelefones().addAll(objDTO.getTelefones());
		
		return cliente;
	}

	public Cliente fromNewDTO(ClienteNewDTO objDTO) {
		
		Cliente cliente = new Cliente(null,objDTO.getNome(),objDTO.getSobrenome(), objDTO.getDataNascimento(),objDTO.getRg(), objDTO.getCpfOuCnpj(),TipoPessoa.toEnum(objDTO.getTipoPessoa()),TipoSexo.toEnum(objDTO.getSexo()),pe.encode(objDTO.getSenha()),objDTO.getEmail());
		cliente.getTelefones().add(objDTO.getTelefone1());
		
		if(objDTO.getTelefone2()!=null){
		   cliente.getTelefones().add(objDTO.getTelefone2());
		}	
		
		EnderecoCliente endereco = new EnderecoCliente(null,objDTO.getCidade(), objDTO.getEstado(), objDTO.getCep(),objDTO.getBairro(),objDTO.getRua(),objDTO.getNumero(),objDTO.getComplemento(), cliente);
		cliente.setEndereco(endereco);
		
		return cliente;
	}
	
	private void updateData(Cliente newObj,Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setSobrenome(obj.getSobrenome());
		newObj.setDataNascimento(obj.getDataNascimento());
		newObj.setRg(obj.getRg());
		newObj.setCpfOuCnpj(obj.getCpfOuCnpj());
		newObj.setSexo(obj.getSexo());
		newObj.setTipoPessoa(obj.getTipoPessoa());
		newObj.setPrestador(obj.getPrestador());
	} 
	
	public URI uploadProfilePicture(MultipartFile multipartfile) {
		UserSpringSecurity user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
			
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartfile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId()+".jpg";
		
		return s3service.uploadFile(imageService.getInputStream(jpgImage,"jpg"), fileName, "image");

	}
	
}

