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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appServices.AppServices.Services.ExperienciasService;
import com.appServices.AppServices.domain.Experiencias;
import com.appServices.AppServices.dto.ExperienciasDTO;
import com.appServices.AppServices.dto.ExperienciasNewDTO;


@RestController
@RequestMapping(value="/experiencias")
public class ExperienciasResource {

	@Autowired
	private ExperienciasService service;
	
	//BUSCA DE EXPERIENCIA POR ID
	@PreAuthorize("hasAnyRole('CLIENTE')") 
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	public ResponseEntity<Experiencias> find(
			@PathVariable Integer id){

		Experiencias objList = service.find(id);
		return ResponseEntity.ok().body(objList);
	}
	
	//INSERÇÃO DE NOVA EXPERIENCIA
	@PreAuthorize("hasAnyRole('PRESTADOR')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ExperienciasNewDTO objDTO){
		Experiencias obj = service.fromNewDTO(objDTO);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//ALTERAÇÃO DE EXPERIÊNCIA
	@PreAuthorize("hasAnyRole('PRESTADOR')")
	@RequestMapping(value="{id}",method = RequestMethod.PUT)
	public ResponseEntity<Experiencias> update(@Valid @RequestBody ExperienciasDTO objDTO,@PathVariable Integer id){
		Experiencias obj = service.fromDTO(objDTO);
		obj.setId(id);
		
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();			
	}
	
	//EXCLUSÃO DE EXPERIÊNCIA
	@PreAuthorize("hasAnyRole('PRESTADOR')")
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	//BUSCA  DE TODAS EXPERIÊNCIAS CADASTRADAS
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ExperienciasDTO>> findAll(){
		List<Experiencias> objList = service.findAll();
		List<ExperienciasDTO> listDto = objList.stream().map(obj -> new ExperienciasDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
}
