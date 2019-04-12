package com.appServices.AppServices.Services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appServices.AppServices.Service.exception.DataIntegrityException;
import com.appServices.AppServices.Service.exception.ObjectNotFoundException;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.ItensSolicitacao;
import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.Profissao;
import com.appServices.AppServices.domain.SolicitacaoServico;
import com.appServices.AppServices.domain.enums.StatusSolicitacao;
import com.appServices.AppServices.dto.SolicitacaoServicoDTO;
import com.appServices.AppServices.dto.SolicitacaoServicoNewDTO;
import com.appServices.AppServices.repositories.ClienteRepository;
import com.appServices.AppServices.repositories.ItensSolicitacaoRepository;
import com.appServices.AppServices.repositories.PrestadorRepository;
import com.appServices.AppServices.repositories.ProfissaoRepository;
import com.appServices.AppServices.repositories.SolicitacaoServicoRepository;

@Service
public class SolicitacaoServicoService {

	@Autowired
	private SolicitacaoServicoRepository repository;

	@Autowired
	private ItensSolicitacaoRepository itensSolicitacaoRepo;	

	@Autowired
	private ProfissaoRepository profissaoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository ;
	
	
	@Autowired
	private EmailServiceSolicitacao emailService;
	
	public SolicitacaoServico find(Integer id) {

		Optional<SolicitacaoServico> objOp = repository.findById(id);

		return objOp.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + ", Tipo: " + SolicitacaoServico.class.getName()));
	}

	@Transactional
	public SolicitacaoServico insert(SolicitacaoServico obj) {
		obj.setId(null);

		obj = repository.save(obj);
		
		itensSolicitacaoRepo.saveAll(obj.getItemServico());
		
		 profissaoRepository.save(obj.getProfissao());
		 
		 emailService.sendOrderConfirmationEmail(obj);

		return obj;
	}

	public SolicitacaoServico update(SolicitacaoServico obj) {
		SolicitacaoServico newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);

	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(e.getMessage());
		}
	}

	public List<SolicitacaoServico> findAll() {
		return repository.findAll();
	}
	
	public SolicitacaoServico fromDTO(SolicitacaoServicoDTO objDTO) {

		SolicitacaoServico solicitacaoServ = new SolicitacaoServico(objDTO.getId(),objDTO.getProdutoServico(),objDTO.getData(),null,null,objDTO.getSituacao());

		return solicitacaoServ;
	}
	
	
	
public SolicitacaoServico fromNewDTO(SolicitacaoServicoNewDTO objDTO,Cliente cliente, Profissao profissao) {
		
		
		SolicitacaoServico SolicitacaoServico = new SolicitacaoServico(objDTO.getId(), objDTO.getProdutoServico()
				,objDTO.getData(), cliente, profissao,StatusSolicitacao.toEnum(objDTO.getSituacao()));
		
		ItensSolicitacao itensSolicitacao1 = new ItensSolicitacao(null, objDTO.getItemSolicitacao1(), SolicitacaoServico);
		ItensSolicitacao itensSolicitacao2 = new ItensSolicitacao(null, objDTO.getItemSolicitacao2(), SolicitacaoServico);
		ItensSolicitacao itensSolicitacao3 = new ItensSolicitacao(null, objDTO.getItemSolicitacao3(), SolicitacaoServico);
		ItensSolicitacao itensSolicitacao4 = new ItensSolicitacao(null, objDTO.getItemSolicitacao4(), SolicitacaoServico);
		ItensSolicitacao itensSolicitacao5 = new ItensSolicitacao(null, objDTO.getItemSolicitacao5(), SolicitacaoServico);
		ItensSolicitacao itensSolicitacao6 = new ItensSolicitacao(null, objDTO.getItemSolicitacao6(), SolicitacaoServico);
		ItensSolicitacao itensSolicitacao7 = new ItensSolicitacao(null, objDTO.getItemSolicitacao7(), SolicitacaoServico);
		ItensSolicitacao itensSolicitacao8 = new ItensSolicitacao(null, objDTO.getItemSolicitacao8(), SolicitacaoServico);

		
		SolicitacaoServico.getItemServico().addAll(Arrays.asList(
				itensSolicitacao1,itensSolicitacao2,itensSolicitacao3,
				itensSolicitacao4,itensSolicitacao5,itensSolicitacao6,
				itensSolicitacao7,itensSolicitacao8));
		
		return SolicitacaoServico;
	}
	
	private void updateData(SolicitacaoServico newObj,SolicitacaoServico obj) {
		newObj.setId(obj.getId());
		newObj.setSituacao(obj.getSituacao());
		
	}
	
public Page<SolicitacaoServico> findByCliente(Integer idCliente,Integer page, Integer linesPerPage,String orderBy,String direction){
		
		PageRequest  pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		
		return repository.findByCliente(cliente,pageRequest);
	}

public Page<SolicitacaoServico> findByProfissao(Integer idProfissao,Integer page, Integer linesPerPage,String orderBy,String direction){
	
	PageRequest  pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
	
	Optional<Profissao> profissao = profissaoRepository.findById(idProfissao);
	
	return repository.findByProfissao(profissao,pageRequest);
}


	
		
}
