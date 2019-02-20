package com.appServices.AppServices.resources;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appServices.AppServices.Services.ProfissaoService;
import com.appServices.AppServices.domain.Profissao;
import com.appServices.AppServices.dto.ProfissaoDTO;


@RestController
@RequestMapping("/profissao")
public class ProfissaoResource {
	
	@Autowired
	private ProfissaoService service;
	
	//pega todos as profissoes
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<List<ProfissaoDTO>> findAll(){
		List<Profissao> objList =service.findAll();
		List<ProfissaoDTO> listDto = objList.stream().map(obj -> new ProfissaoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	
}
