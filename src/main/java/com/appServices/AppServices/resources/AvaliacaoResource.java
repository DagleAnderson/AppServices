package com.appServices.AppServices.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appServices.AppServices.Services.AvaliacaoService;
import com.appServices.AppServices.Services.ClienteService;
import com.appServices.AppServices.Services.PrestadorService;
import com.appServices.AppServices.domain.Avaliacao;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Prestador;

@RestController
@RequestMapping(value="/avaliacao")
public class AvaliacaoResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PrestadorService prestadorService;
	
	@Autowired
	private AvaliacaoService service;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> Insert(
			@Valid @RequestBody Avaliacao obj,
			@RequestParam(value="cliente",defaultValue="0") Integer cliente,
			@RequestParam(value="prestador",defaultValue="0") Integer prestador){
		
		Cliente cli = clienteService.find(cliente);
		Prestador prest = prestadorService.find(prestador) ;
		
		
		
		
		Avaliacao newObj = service.fromNew(obj, cli, prest);
	
		
		obj = service.insert(newObj);
		
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
