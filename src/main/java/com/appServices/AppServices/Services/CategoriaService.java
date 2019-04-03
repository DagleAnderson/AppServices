package com.appServices.AppServices.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appServices.AppServices.Service.exception.ObjectNotFoundException;
import com.appServices.AppServices.domain.Categoria;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.repositories.CategoriaRepository;
import com.appServices.AppServices.security.UserSpringSecurity;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	
	public Categoria findById(Integer id) {
		
		UserSpringSecurity user = UserService.authenticated();
		
		
		Optional<Categoria> objOp = repository.findById(id);
		
		return objOp.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())
				);
	}
	
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
}
