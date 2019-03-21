package com.appServices.AppServices.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.appServices.AppServices.Service.exception.ObjectNotFoundException;
import com.appServices.AppServices.domain.Curriculo;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.Profissao;
import com.appServices.AppServices.dto.CurriculoNewDTO;
import com.appServices.AppServices.repositories.CurriculoRepository;
import com.appServices.AppServices.repositories.CursosRepository;
import com.appServices.AppServices.repositories.PrestadorRepository;

@Service
public class CurriculoService {

	@Autowired
	private CurriculoRepository repository;
	
	@Autowired
	private CursosRepository cursoRepository;
	
	private PrestadorRepository prestadorRepository;

	
	public Curriculo find(Integer id) {
			
		Optional<Curriculo> objOp =  repository.findById(id);
		
		return objOp.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + ", Tipo:" + Curriculo.class.getName())
				);
	}
	
public Page<Curriculo> search(Integer idPrestador,Integer page, Integer linesPerPage,String orderBy,String direction){
		
		PageRequest  pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Optional<Prestador> prestador = prestadorRepository.findById(idPrestador); 
		return repository.search(prestador,pageRequest);
	}
	

	
	public Curriculo insert(Curriculo obj) {
		obj.setId(null);
		
		obj = repository.save(obj);	
		
		cursoRepository.saveAll(obj.getCursos());
		
		
		return obj;
	}
	
	
	public Curriculo newFromDTO(CurriculoNewDTO objDTO) {
		
		Prestador prestador = new Prestador();
		prestador.setId(objDTO.getPrestador());
		
		
		
		Curriculo curriculo = new Curriculo(null, prestador);
	
		/*Cursos curso1 = new Cursos(null, objDTO.getDescricao1(),objDTO.getInstituicao1(),objDTO.getDescricao1(), curriculo);
		curriculo.getCursos().add(curso1);
		
		if((objDTO.getDescricao2()!=null) && (objDTO.getDuracao2()!=null) && (objDTO.getDescricao2())!=null) {
			Cursos curso2 = new Cursos(null, objDTO.getDescricao2(),objDTO.getInstituicao2(),objDTO.getDescricao2(), curriculo);
			curriculo.getCursos().add(curso2);
		}
		if((objDTO.getDescricao3()!=null) && (objDTO.getDuracao3()!=null) && (objDTO.getDescricao3())!=null) {
			Cursos curso3 = new Cursos(null, objDTO.getDescricao3(),objDTO.getInstituicao3(),objDTO.getDescricao3(), curriculo);
			curriculo.getCursos().add(curso3);	
		}*/
	
		return curriculo;	
	}
	
}
