package com.appServices.AppServices.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appServices.AppServices.domain.Estado;
import com.appServices.AppServices.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repository;
	
	public List<Estado> findAll(){
		return repository.findAllByOrderByName();	
	}
}
