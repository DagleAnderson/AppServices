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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appServices.AppServices.Services.ClienteService;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.dto.ClienteDTO;
import com.appServices.AppServices.dto.ClienteNewDTO;

@RestController
@RequestMapping(value="/cliente")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	 
	//BUSCAR CLIENTE POR ID
		@RequestMapping(value="/{id}",method = RequestMethod.GET)
		public ResponseEntity<Cliente> find(@PathVariable Integer id){
			Cliente objOp = service.find(id);
			
			return ResponseEntity.ok().body(objOp);
		}
	
	//BURCAR CLIENTE POR EMAIL
		@RequestMapping(value="/email",method = RequestMethod.GET)
		public ResponseEntity<Cliente> find(@RequestParam(value="value") String email){
			Cliente obj = service.findByEmail(email);
			
			return ResponseEntity.ok().body(obj);
		}
	
	//INSERÇÃO DE NOVO CLIENTE
		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<Void> Insert(@Valid @RequestBody ClienteNewDTO objDTO){
			Cliente obj = service.fromNewDTO(objDTO);
			obj = service.insert(obj);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(obj.getId()).toUri();
			
			return ResponseEntity.created(uri).build();
		}
	
	//ALTERAR DADOS DO CLIENTE	
		@PreAuthorize("hasAnyRole('CLIENTE')")
		@RequestMapping(value= "/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO,@PathVariable Integer id){
			
			Cliente obj = service.fromDTO(objDTO);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
			
		}
		
	//DELETAR CLIENTE
		@PreAuthorize("hasAnyRole('CLIENTE')")
		@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id){
			service.delete(id);
			
			return ResponseEntity.noContent().build();	
		}
	
	//BUSCAR TODOS OS CLIENTES
		@PreAuthorize("hasAnyRole('CLIENTE')")
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<ClienteDTO>> findAll(){
			List<Cliente> objList =service.findAll();
			List<ClienteDTO> listDto = objList.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
			
			return ResponseEntity.ok().body(listDto);
		}
	
	//UPLOAD DE IMAGEM DE PERFIL
		@RequestMapping(value="/picture",method = RequestMethod.POST)
		public ResponseEntity<Void> uploadProfilePicture(@RequestParam("file") MultipartFile file){
			URI uri = service.uploadProfilePicture(file);
			return ResponseEntity.created(uri).build();
		}
}
