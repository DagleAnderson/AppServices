package com.appServices.AppServices.Services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.appServices.AppServices.Service.exception.DataIntegrityException;
import com.appServices.AppServices.Service.exception.ObjectNotFoundException;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.SolicitacaoServico;
import com.appServices.AppServices.domain.enums.TipoSituacao;
import com.appServices.AppServices.domain.ItensOrcamento;
import com.appServices.AppServices.dto.OrcamentoDTO;
import com.appServices.AppServices.dto.OrcamentoNewDTO;
import com.appServices.AppServices.repositories.ItensOrcamentoRepository;
import com.appServices.AppServices.repositories.OrcamentoRepository;


@Service
public class OrcamentoService {
	
	@Autowired
	private OrcamentoRepository repository;

	@Autowired
	private ItensOrcamentoRepository itensOrcamentoRepo;
	
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

		obj = repository.save(obj);
		
		itensOrcamentoRepo.saveAll(obj.getItensOrcamento());
		
		 emailService.sendOrderConfirmationEmail(obj);

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
	
	public Orcamento fromDTO(OrcamentoDTO objDTO,Cliente cliente, Prestador prestador,SolicitacaoServico solicitacao) {

		Orcamento orcamento = new Orcamento(objDTO.getId(), objDTO.getProdutoServico(),prestador, cliente,objDTO.getTotal(), objDTO.getDesconto(),objDTO.getSituacao(),solicitacao);
		return orcamento;
	}
	
	
	
public Orcamento fromNewDTO(OrcamentoNewDTO objDTO,Cliente cliente, Prestador prestador,SolicitacaoServico solicitacao) {
		
		
	Orcamento orcamento = new Orcamento(objDTO.getId(), objDTO.getProdutoServico(),prestador, cliente,objDTO.getTotal(), objDTO.getDesconto(),TipoSituacao.toEnum(objDTO.getSituacao()),solicitacao);
		
		ItensOrcamento itensOrcamento1 = new ItensOrcamento(null,objDTO.getItemOrcamento1(),objDTO.getQuantidade1(),objDTO.getDesconto1(),objDTO.getValorItem1(), orcamento);
		ItensOrcamento itensOrcamento2 = new ItensOrcamento(null, objDTO.getItemOrcamento2(),objDTO.getQuantidade2(),objDTO.getDesconto2(),objDTO.getValorItem2(), orcamento);
		ItensOrcamento itensOrcamento3 = new ItensOrcamento(null, objDTO.getItemOrcamento3(),objDTO.getQuantidade3(),objDTO.getDesconto3(),objDTO.getValorItem3(), orcamento);
		
		orcamento.getItensOrcamento().addAll(Arrays.asList(itensOrcamento1,itensOrcamento2,itensOrcamento3));
		
		return orcamento;
	}
	
	private void updateData(Orcamento newObj,Orcamento obj) {
		newObj.setId(obj.getId());
		newObj.setProdutoServico(obj.getProdutoServico());
		newObj.setDesconto(obj.getDesconto());
		newObj.setSituacao(obj.getSituacao());
		
	}
}
