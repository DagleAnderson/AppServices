package com.appServices.AppServices.resources;




import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appServices.AppServices.Services.CurriculoService;
import com.appServices.AppServices.domain.Curriculo;
import com.appServices.AppServices.dto.CurriculoNewDTO;


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
	
	@PreAuthorize("hasAnyRole('PRESTADOR')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Curriculo> insert(@RequestBody CurriculoNewDTO objDTO){
		
		Curriculo obj = service.newFromDTO(objDTO);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
/**	//PUT CURSOS
	@RequestMapping(value="/{idPrestador}/curriculo",method = RequestMethod.PUT)
	public ResponseEntity<Cursos> updateCursos(
			@Valid @RequestBody CursosDTO objDTO, 
			@PathVariable Integer idPrestador,
			@RequestParam(value="id",defaultValue="0") Integer id,
			@RequestParam(value="id",defaultValue="0")Integer curso){
		
		Cursos obj = cursoService.fromDTO(objDTO);
		obj.setId(curso);
		
		obj = cursoService.upadate(obj);
		
		return ResponseEntity.noContent().build();			
	}**/
	
	
	/** //DELETE CURSO
	@RequestMapping(value="/{idPrestador}/curriculo",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCurso(
			@PathVariable Integer idPrestador,
			@RequestParam(value="id",defaultValue="0") Integer id,
			@RequestParam(value="id",defaultValue="0")Integer curso){
		
		cursoService.delete(curso);
		
		return ResponseEntity.noContent().build();
	}**/
	
	//PUT EXPERIENCIA
	/**@RequestMapping(value="/{idPrestador}/curriculo",method = RequestMethod.PUT)
	public ResponseEntity<Experiencias> updateExperiencia(
			@Valid @RequestBody ExperienciasDTO objDTO, 
			@PathVariable Integer idPrestador,
			@RequestParam(value="id",defaultValue="0") Integer id,
			@RequestParam(value="id",defaultValue="0")Integer experiencia){
		
		Experiencias obj = expeService.fromDTO(objDTO);
		obj.setId(experiencia);
		
		obj = expeService.update(obj);
		
		return ResponseEntity.noContent().build();			
	}**/
	
	//DELETE EXPERIENCIA
	/**@RequestMapping(value="/{idPrestador}/curriculo",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteExperiencia(
			@PathVariable Integer idPrestador,
			@RequestParam(value="id",defaultValue="0") Integer id,
			@RequestParam(value="id",defaultValue="0")Integer experiencia){
		
		expeService.delete(experiencia);
		
		return ResponseEntity.noContent().build();
	}**/

	
}
