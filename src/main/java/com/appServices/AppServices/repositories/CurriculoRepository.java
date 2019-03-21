package com.appServices.AppServices.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.appServices.AppServices.domain.Curriculo;
import com.appServices.AppServices.domain.Prestador;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Integer>  {

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Curriculo obj INNER JOIN obj.prestador prest WHERE prest IN :prestador")
	Page<Curriculo> search(@Param("prestador") Optional<Prestador> prestador, Pageable pageRequest);

}
