package com.appServices.AppServices.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appServices.AppServices.domain.Cidade;
import com.appServices.AppServices.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public List<Cidade> findByEstado(Integer estado){
		return repository.findCidades(estado);
	}
}
