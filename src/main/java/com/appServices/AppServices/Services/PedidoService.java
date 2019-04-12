package com.appServices.AppServices.Services;


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
import com.appServices.AppServices.domain.enums.StatusAtendimento;
import com.appServices.AppServices.domain.enums.StatusPagamento;
import com.appServices.AppServices.domain.enums.StatusSolicitacao;
import com.appServices.AppServices.domain.enums.TipoSituacao;
import com.appServices.AppServices.domain.enums.TipoUnidade;
import com.appServices.AppServices.dto.PedidoDTO;
import com.appServices.AppServices.dto.PedidoNewDTO;
import com.appServices.AppServices.repositories.ItensPedidoRepository;
import com.appServices.AppServices.repositories.OrcamentoRepository;
import com.appServices.AppServices.repositories.PedidoRepository;
import com.appServices.AppServices.security.UserSpringSecurity;

@Service
public class PedidoService {
	//atributos internos para obternção de valores
	private String item;
	private Double quantidade;
	private Integer unidade;
	private Double descontoItem;
	private Double valorItem;
	

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ItensPedidoRepository itensPedidoRepo;
	
	private OrcamentoService orcamentoService;
	
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
		
		emailServicePedido.sendOrderConfirmationEmail(obj);
		
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

		Pedido Pedido = new Pedido(objDTO.getId(),objDTO.getProdutoServico(),prestador, cliente, objDTO.getDesconto(),objDTO.getData(),orcamento,objDTO.getAtendimento());		
		return Pedido;
	}
	
	
	
public Pedido fromNewDTO(Pedido objDTO,Cliente cliente, Prestador prestador,Orcamento orcamento) {
		
		
	Pedido pedido = new Pedido(objDTO.getId(),objDTO.getProdutoServico(),prestador, cliente, objDTO.getDesconto(),objDTO.getData(),orcamento,StatusAtendimento.PENDENTE);
	extractArrayItens(objDTO,pedido);
		
		return pedido;
	}
	
	private void extractArrayItens(Pedido obj,Pedido pedido) {

		for(int x=0; x < obj.getItensPedido().size();x++ ) {
			 item = obj.getItensPedido().get(x).getItem();
			 quantidade = obj.getItensPedido().get(x).getQuantidade();
			 unidade = obj.getItensPedido().get(x).getUnidade().getCodigo();
			 descontoItem=obj.getItensPedido().get(x).getDesconto();
			 valorItem = obj.getItensPedido().get(x).getValor();
			 
			 ItensPedido itensPedido = new ItensPedido(null,item,quantidade,TipoUnidade.toEnum(unidade),descontoItem,valorItem,pedido);
			 pedido.getItensPedido().add(itensPedido);
			 
		}
	}	
	
	
	private void updateData(Pedido newObj,Pedido obj) {
		newObj.setAtendimento(obj.getAtendimento());
		
	}
	
	public 	Page<PedidoDTO> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		UserSpringSecurity user = 	UserService.authenticated();
		if(user==null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		Cliente cliente = clienteService.find(user.getId()); 
		
		return repository.findByCliente(cliente, pageRequest);
	}
}
