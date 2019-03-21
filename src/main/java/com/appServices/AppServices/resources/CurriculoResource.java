package com.appServices.AppServices.resources;




import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appServices.AppServices.Services.CurriculoService;
import com.appServices.AppServices.domain.Curriculo;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.dto.PrestadorDTO;

@RestController
@RequestMapping("/curriculo")
public class CurriculoResource {
	
	@Autowired
	private CurriculoService service;	
	
	@PreAuthorize("hasAnyRole('CLIENTE')")
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Curriculo> find(@PathVariable Integer id ){
		Curriculo objOp = service.find(id);
		
		return ResponseEntity.ok().body(objOp);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Curriculo>> findByPrestadorPage(
			@RequestParam(value="prestador",defaultValue ="0") Integer prestador,
			@RequestParam(value="page",defaultValue ="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue ="24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue ="id")	String orderBy,
			@RequestParam(value="direction",defaultValue ="ASC") String direction
			){
		
		Page<Curriculo> obj =service.search(prestador, page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
