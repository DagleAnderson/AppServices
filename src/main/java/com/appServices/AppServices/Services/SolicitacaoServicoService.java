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
import com.appServices.AppServices.domain.ItensSolicitacao;
import com.appServices.AppServices.domain.Profissao;
import com.appServices.AppServices.domain.SolicitacaoServico;
import com.appServices.AppServices.domain.enums.StatusSolicitacao;
import com.appServices.AppServices.dto.SolicitacaoServicoDTO;
import com.appServices.AppServices.dto.SolicitacaoServicoNewDTO;
import com.appServices.AppServices.repositories.ItensSolicitacaoRepository;
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
	
	public SolicitacaoServico fromDTO(SolicitacaoServicoDTO objDTO,Cliente cliente, Profissao profissao) {

		SolicitacaoServico solicitacaoServ = new SolicitacaoServico(objDTO.getId(),objDTO.getProdutoServico(),objDTO.getData(),cliente,null,objDTO.getStatusSolicitacao());

		return solicitacaoServ;
	}
	
	
	
public SolicitacaoServico fromNewDTO(SolicitacaoServicoNewDTO objDTO,Cliente cliente, Profissao profissao) {
		
		
		SolicitacaoServico SolicitacaoServico = new SolicitacaoServico(objDTO.getId(), objDTO.getProdutoServico()
				,objDTO.getData(), cliente, profissao,StatusSolicitacao.toEnum(objDTO.getStatusSolicitacao()));
		
		ItensSolicitacao itensSolicitacao1 = new ItensSolicitacao(null, objDTO.getItemSolicitacao1(), SolicitacaoServico);
		ItensSolicitacao itensSolicitacao2 = new ItensSolicitacao(null, objDTO.getItemSolicitacao2(), SolicitacaoServico);
		ItensSolicitacao itensSolicitacao3 = new ItensSolicitacao(null, objDTO.getItemSolicitacao3(), SolicitacaoServico);
		
		SolicitacaoServico.getItemServico().addAll(Arrays.asList(itensSolicitacao1,itensSolicitacao2,itensSolicitacao3));
		
		return SolicitacaoServico;
	}
	
	private void updateData(SolicitacaoServico newObj,SolicitacaoServico obj) {
		newObj.setId(obj.getId());
		newObj.setProdutoServico(obj.getProdutoServico());;
		
	}
	
		
}
