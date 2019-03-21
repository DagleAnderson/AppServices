package com.appServices.AppServices.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.Profissao;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Integer>  {
	
	//Busca de prestador por Profissao
		@Transactional(readOnly=true)
		@Query("SELECT DISTINCT obj FROM Prestador obj INNER JOIN obj.profissao prof WHERE prof IN :profissao")
		Page<Prestador> search(@Param("profissao") Optional<Profissao> profissao, Pageable pageRequest);
}
