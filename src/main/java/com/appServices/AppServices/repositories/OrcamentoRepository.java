package com.appServices.AppServices.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.SolicitacaoServico;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {
	//Busca de orcamentos por solicitacao
	
		@Transactional(readOnly=true)
		@Query("SELECT DISTINCT obj FROM Orcamento obj INNER JOIN obj.solicitacao solic WHERE solic IN :solicitacao")
		Page<Orcamento> search(@Param("solicitacao") Optional<SolicitacaoServico> solicitacao, Pageable pageRequest);
}
