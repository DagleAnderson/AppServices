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

import com.appServices.AppServices.Service.exception.AuthorizationException;
import com.appServices.AppServices.Service.exception.DataIntegrityException;
import com.appServices.AppServices.Service.exception.ObjectNotFoundException;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.ItensPedido;
import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.Pedido;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.SolicitacaoServico;
import com.appServices.AppServices.domain.enums.StatusPagamento;
import com.appServices.AppServices.domain.enums.TipoSituacao;
import com.appServices.AppServices.dto.PedidoDTO;
import com.appServices.AppServices.dto.PedidoNewDTO;
import com.appServices.AppServices.repositories.ItensPedidoRepository;
import com.appServices.AppServices.repositories.PedidoRepository;
import com.appServices.AppServices.security.UserSpringSecurity;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ItensPedidoRepository itensPedidoRepo;	
	
	@Autowired
	private EmailServicePedido emailServicePedido;
	
	@Autowired
	private ClienteService clienteService;
	
	public Pedido find(Integer id) {

		Optional<Pedido> objOp = repository.findById(id);

		return objOp.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + ", Tipo: " + SolicitacaoServico.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);

		obj = repository.save(obj);
		
		itensPedidoRepo.saveAll(obj.getItensPedido());
		
		emailServicePedido.sendOrderConfirmationHtmlEmail(obj);
		
		return obj;
	}

	public Pedido update(Pedido obj) {
		Pedido newObj = find(obj.getId());
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

	public List<Pedido> findAll() {
		return repository.findAll();
	}
	
	public Pedido fromDTO(PedidoDTO objDTO,Cliente cliente, Prestador prestador,Orcamento orcamento) {

		Pedido Pedido = new Pedido(objDTO.getId(),objDTO.getProdutoServico(),prestador, cliente,objDTO.getTotal(), objDTO.getDesconto(),objDTO.getData(),objDTO.getSituacao(),objDTO.getStatusPagamento(),orcamento);		
		return Pedido;
	}
	
	
	
public Pedido fromNewDTO(PedidoNewDTO objDTO,Cliente cliente, Prestador prestador,Orcamento orcamento) {
		
		
	Pedido pedido = new Pedido(objDTO.getId(),objDTO.getProdutoServico(),prestador, cliente,objDTO.getTotal(), objDTO.getDesconto(),objDTO.getData(), TipoSituacao.toEnum(objDTO.getSituacao()),StatusPagamento.toEnum(objDTO.getStatusPagamento()),orcamento);
		
		ItensPedido itensPedido1 = new ItensPedido(null, objDTO.getItemPedido1(),objDTO.getValorItem1(), pedido);
		ItensPedido itensPedido2 = new ItensPedido(null, objDTO.getItemPedido2(),objDTO.getValorItem2(), pedido);
		ItensPedido itensPedido3 = new ItensPedido(null, objDTO.getItemPedido3(),objDTO.getValorItem3(), pedido);
		
		pedido.getItensPedido().addAll(Arrays.asList(itensPedido1,itensPedido2,itensPedido3));
		
		return pedido;
	}
	
	private void updateData(Pedido newObj,Pedido obj) {
		newObj.setId(obj.getId());
		newObj.setProdutoServico(obj.getProdutoServico());
		newObj.setDesconto(obj.getDesconto());
		newObj.setTotal(obj.getTotal());
		newObj.setSituacao(obj.getSituacao());
		
	}
	
	public 	Page<Pedido> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		UserSpringSecurity user = 	UserService.authenticated();
		if(user==null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		Cliente cliente = clienteService.find(user.getId()); 
		
		return repository.findByCliente(cliente, pageRequest);
	}
}
