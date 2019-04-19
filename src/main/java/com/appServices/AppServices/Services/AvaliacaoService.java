package com.appServices.AppServices.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appServices.AppServices.domain.Avaliacao;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.repositories.AvaliacoesRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacoesRepository repository;
	
	@Autowired
	private PrestadorService prestadorService;
	
	@Transactional
	public Avaliacao insert(Avaliacao obj) {
		obj.setId(null);
		
		obj = repository.save(obj);
		
		Prestador prestador = new Prestador();
		prestador = obj.getPrestador();
		prestador.getAvaliacoes().add(obj);
		
		this.prestadorService.update(prestador);
		

		//emailService.sendOrderConfirmationEmail(obj);


		return obj;
	}
	
	public Avaliacao fromNew(Avaliacao obj,Cliente cliente, Prestador prestador) {

		
		Avaliacao avaliacao = new Avaliacao(obj.getId(),cliente, prestador,obj.getEstrelas(),obj.getComentario());
		
		return avaliacao;
		}
}
