package com.appServices.AppServices.Services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appServices.AppServices.Service.exception.ObjectNotFoundException;
import com.appServices.AppServices.domain.PagSeguro;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.repositories.PagSeguroRepository;

@Service
public class PagSeguroService {

	@Autowired
	private PagSeguroRepository repository;

	
	public PagSeguro findById(Integer id) {
		
		Optional<PagSeguro> objOp = repository.findById(id);
		
		return objOp.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Prestador.class.getName())
				);
	}


	@Transactional
	public PagSeguro insert(PagSeguro obj) {
		obj.setId(null);

		obj = repository.save(obj);
		
		return obj;
	}
	
	/**public PagSeguro update(PagSeguro obj) {
		PagSeguro newObj = findByNcode(obj.getnCode());
		updateData(newObj, obj);
		
		return repository.save(newObj);

	}**/

	private void updateData(PagSeguro newObj, PagSeguro obj) {
			newObj.setId(obj.getId());
			newObj.setCliente(obj.getCliente());
			newObj.setValor(obj.getValor());;
			newObj.setStatusPagamento(obj.getStatusPagamento());;
			newObj.setURLCreatePag(obj.getURLCreatePag());
			newObj.setnCode(obj.getnCode());
			newObj.setnType(obj.getnType()); 	

		
	}

}
