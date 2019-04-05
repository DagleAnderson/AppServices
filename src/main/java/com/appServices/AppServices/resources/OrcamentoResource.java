package com.appServices.AppServices.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appServices.AppServices.Services.ClienteService;
import com.appServices.AppServices.Services.OrcamentoService;
import com.appServices.AppServices.Services.PrestadorService;
import com.appServices.AppServices.Services.SolicitacaoServicoService;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.SolicitacaoServico;
import com.appServices.AppServices.dto.OrcamentoDTO;
import com.appServices.AppServices.dto.OrcamentoNewDTO;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoResource implements Serializable{
	private static final long serialVersionUID = 1L;

	@Autowired
	private OrcamentoService service;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PrestadorService prestadorService;
	
	@Autowired
	private SolicitacaoServicoService solicitacaoService;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Orcamento> find(@PathVariable Integer id){
		Orcamento objOp = service.find(id);
		
		return ResponseEntity.ok().body(objOp);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> Insert(
			@Valid @RequestBody Orcamento obj,
			@RequestParam(value="cliente",defaultValue="0") Integer cliente,
			@RequestParam(value="prestador",defaultValue="0") Integer prestador,
			@RequestParam(value="solicitacaoServico",defaultValue="0") Integer solicitacao){
		
		Cliente cli = clienteService.find(cliente);
		
		Prestador prest = prestadorService.find(prestador) ;
		
		SolicitacaoServico solicit = solicitacaoService.find(solicitacao); 
		
		
		
		Orcamento Newobj = service.fromNew(obj, cli, prest,solicit);
	
		
		obj = service.insert(Newobj);
		
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('PRESTADOR')")
	@RequestMapping(value= "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@Valid @RequestBody OrcamentoDTO objDTO,@PathVariable Integer id,
			@RequestParam(value="cliente",defaultValue="0") Integer cliente,
			@RequestParam(value="prestador",defaultValue="0") Integer prestador,
			@RequestParam(value="solicitacaoServico",defaultValue="0") Integer solicitacao){
		
		Cliente cli = clienteService.find(cliente);
		Prestador prest = prestadorService.find(prestador);
		
		SolicitacaoServico solicit =solicitacaoService.find(solicitacao); 
	
		Orcamento obj = service.fromDTO(objDTO, cli, prest,solicit);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@PreAuthorize("hasAnyRole('PRESTADOR')")
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<OrcamentoDTO>> findAll(){
		List<Orcamento> objList =service.findAll();
		List<OrcamentoDTO> listDto = objList.stream().map(obj -> new OrcamentoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	//GetList Orcamentos por solilicitação
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public ResponseEntity<Page<OrcamentoDTO>> findAllBySolicitacaoPage(
			@RequestParam(value="solicitacaoServico",defaultValue="0") Integer solicitacao,
			@RequestParam(value="page",defaultValue ="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue ="24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue ="id")	String orderBy,
			@RequestParam(value="direction",defaultValue ="ASC") String direction
			){
		
		Page<Orcamento> objList =service.search(solicitacao, page, linesPerPage, orderBy, direction);
		
		Page<OrcamentoDTO> listOrcamento= objList.map(obj -> new OrcamentoDTO(obj));
		
		return ResponseEntity.ok().body(listOrcamento);
	}
}
