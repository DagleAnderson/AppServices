package com.appServices.AppServices.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.appServices.AppServices.Services.ProfissaoService;
import com.appServices.AppServices.Services.SolicitacaoServicoService;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Profissao;
import com.appServices.AppServices.domain.SolicitacaoServico;
import com.appServices.AppServices.dto.SolicitacaoServicoDTO;
import com.appServices.AppServices.dto.SolicitacaoServicoNewDTO;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoServicoResource {
	
	@Autowired
	private SolicitacaoServicoService service;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProfissaoService profissaoService;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<SolicitacaoServico> find(@PathVariable Integer id){
		SolicitacaoServico objOp = service.find(id);
		
		return ResponseEntity.ok().body(objOp);
	}
	
	@PreAuthorize("hasAnyRole('CLIENTE')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> Insert(
			@Valid @RequestBody SolicitacaoServicoNewDTO objDTO,
			@RequestParam(value="cliente",defaultValue="0") Integer cliente,
			@RequestParam(value="profissao",defaultValue="0") Integer profissao){
		
		Cliente cli = clienteService.find(cliente);
		
		Profissao prof = profissaoService.find(profissao) ;
		
		SolicitacaoServico obj = service.fromNewDTO(objDTO,cli,prof);
		
		obj = service.insert(obj);
		
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('CLIENTE')")
	@RequestMapping(value= "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@Valid @RequestBody SolicitacaoServicoDTO objDTO,@PathVariable Integer id,
			@RequestParam(value="cliente",defaultValue="0") Integer cliente,
			@RequestParam(value="profissao",defaultValue="0") Integer profissao){
		
		Cliente cli = clienteService.find(cliente);
	
		SolicitacaoServico obj = service.fromDTO(objDTO,cli,null);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@PreAuthorize("hasAnyRole('CLIENTE')")
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SolicitacaoServicoDTO>> findAll(
			@RequestParam(value="cliente",defaultValue="0") Integer cliente){
		List<SolicitacaoServico> objList =service.findAll();
		List<SolicitacaoServicoDTO> listDto = objList.stream().map(obj -> new SolicitacaoServicoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}

}
