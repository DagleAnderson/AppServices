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
import com.appServices.AppServices.Service.exception.DataIntegrityException;
import com.appServices.AppServices.Service.exception.ObjectNotFoundException;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.PagamentoComDinheiro;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.SolicitacaoServico;
import com.appServices.AppServices.domain.enums.TipoSituacao;
import com.appServices.AppServices.domain.enums.TipoUnidade;
import com.appServices.AppServices.domain.ItensOrcamento;
import com.appServices.AppServices.dto.OrcamentoDTO;
import com.appServices.AppServices.repositories.FormaDePagamentoRepository;
import com.appServices.AppServices.repositories.ItensOrcamentoRepository;
import com.appServices.AppServices.repositories.OrcamentoRepository;
import com.appServices.AppServices.repositories.SolicitacaoServicoRepository;


@Service
public class OrcamentoService {
	//atributos internos para obternção de valores
	private String item;
	private Double quantidade;
	private Integer unidade;
	private Double descontoItem;
	private Double valorItem;
	
	@Autowired
	private OrcamentoRepository repository;

	@Autowired
	private ItensOrcamentoRepository itensOrcamentoRepo;
	
	
	@Autowired 
	private SolicitacaoServicoRepository solicitacaoRepository;
	

	@Autowired
	private EmailServiceOrcamento emailService;
	
	public Orcamento find(Integer id) {

		Optional<Orcamento> objOp = repository.findById(id);

		return objOp.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + ", Tipo: " + SolicitacaoServico.class.getName()));
	}

	@Transactional
	public Orcamento insert(Orcamento obj) {
		obj.setId(null);
		obj.getFormaDePagamento().setOrcamento(obj);
		itensOrcamentoRepo.saveAll(obj.getItensOrcamento());
		
		obj = repository.save(obj);

		 emailService.sendOrderConfirmationHtmlEmail(obj);


		return obj;
	}

	public Orcamento update(Orcamento obj) {
		Orcamento newObj = find(obj.getId());
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

	public List<Orcamento> findAll() {
		return repository.findAll();
	}
	
	
	public Orcamento fromDTO(OrcamentoDTO objDTO) {

		Orcamento orcamento = new Orcamento(objDTO.getId(), objDTO.getProdutoServico(),objDTO.getData(),null, null, objDTO.getDesconto(),objDTO.getFormaDePagamento(), objDTO.getSituacao(),null);
		return orcamento;
	}
	
	
	
	public Orcamento fromNew(Orcamento obj,Cliente cliente, Prestador prestador,SolicitacaoServico solicitacao) {

		
	Orcamento orcamento = new Orcamento(obj.getId(), obj.getProdutoServico(),obj.getData(), prestador, cliente, obj.getDesconto(),obj.getFormaDePagamento(),TipoSituacao.PENDENTE,solicitacao);
	
	extractArrayItens(obj,orcamento);
	
	
	return orcamento;
	}

	private void extractArrayItens(Orcamento obj,Orcamento orcamento) {

			for(int x=0; x < obj.getItensOrcamento().size();x++ ) {
				 item = obj.getItensOrcamento().get(x).getItem();
				 quantidade = obj.getItensOrcamento().get(x).getQuantidade();
				 unidade = obj.getItensOrcamento().get(x).getUnidade().getCodigo();
				 descontoItem=obj.getItensOrcamento().get(x).getDesconto();
				 valorItem = obj.getItensOrcamento().get(x).getValor();
				 
				 ItensOrcamento itensOrcamento = new ItensOrcamento(null,item,quantidade,TipoUnidade.toEnum(unidade),descontoItem,valorItem,orcamento);
				 orcamento.getItensOrcamento().add(itensOrcamento);
			}
	}

	private void updateData(Orcamento newObj,Orcamento obj) {
		newObj.setId(obj.getId());
		newObj.setSituacao(obj.getSituacao());
		
	}
	
	public Page<Orcamento> search(Integer idSolicitacao,Integer page, Integer linesPerPage,String orderBy,String direction){
		
		PageRequest  pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Optional<SolicitacaoServico> solicitacao = solicitacaoRepository.findById(idSolicitacao);
		
		return repository.search(solicitacao,pageRequest);
	}
}
