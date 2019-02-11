package com.appServices.AppServices.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	/**	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<List<ProfissaoDTO>> findAll(){
		List<Profissao> objList =service.findAll();
		List<ProfissaoDTO> listDto = objList.stream().map(obj -> new ProfissaoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}**/
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProfissaoDTO>> findAllPage(
			@RequestParam(value="categoria",defaultValue ="0") Integer categoria,
			@RequestParam(value="page",defaultValue ="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue ="24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue ="id")	String orderBy,
			@RequestParam(value="direction",defaultValue ="ASC") String direction
			){
		
		//Integer categoriaDecod = Integer.parseInt(categoria);
		Page<Profissao> objList =service.search(categoria, page, linesPerPage, orderBy, direction);
		
		Page<ProfissaoDTO> listProfissao= objList.map(obj -> new ProfissaoDTO(obj));
		
		return ResponseEntity.ok().body(listProfissao);
	}
}
