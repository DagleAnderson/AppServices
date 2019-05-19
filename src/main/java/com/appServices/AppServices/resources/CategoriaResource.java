package com.appServices.AppServices.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appServices.AppServices.Services.CategoriaService;
import com.appServices.AppServices.Services.ProfissaoService;
import com.appServices.AppServices.domain.Categoria;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Profissao;
import com.appServices.AppServices.dto.CategoriaDTO;
import com.appServices.AppServices.dto.ProfissaoDTO;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@Autowired
	private ProfissaoService serviceProfissao;
	
	
	//BUSCA DE CATEGORORIA POR ID
		@RequestMapping(value="/{id}",method = RequestMethod.GET)
		public ResponseEntity<Categoria> findById(@PathVariable Integer id){
			Categoria objOp = service.findById(id);
			
			return ResponseEntity.ok().body(objOp);
		}
	

	//BUSCA DE TODOS AS CATEGORIAS
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<CategoriaDTO>> findAll(){
			List<Categoria> objList = service.findAll();
			List<CategoriaDTO> listDto = objList.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
		}
		
	//BUSCAR PROFISSOÕES POR CATEGORIA	
		//	@PreAuthorize("hasAnyRole('ADMIN')")
			@RequestMapping(value="{id}/profissoes",method = RequestMethod.GET)
			public ResponseEntity<Page<ProfissaoDTO>> findAllPage(
					@PathVariable Integer id,
					@RequestParam(value="page",defaultValue ="0") Integer page, 
					@RequestParam(value="linesPerPage",defaultValue ="24") Integer linesPerPage,
					@RequestParam(value="orderBy",defaultValue ="id")	String orderBy,
					@RequestParam(value="direction",defaultValue ="ASC") String direction
					){
				
				Page<Profissao> objList =serviceProfissao.search(id, page, linesPerPage, orderBy, direction);
				
				Page<ProfissaoDTO> listProfissao= objList.map(obj -> new ProfissaoDTO(obj));
				
				return ResponseEntity.ok().body(listProfissao);
			}
			
		
}

