package com.appServices.AppServices.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.SolicitacaoServico;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {
		//busca por solicitação
		@Transactional(readOnly=true)
		@Query("SELECT DISTINCT obj FROM Orcamento obj INNER JOIN obj.solicitacao solic WHERE solic IN :solicitacao")
		Page<Orcamento> search(@Param("solicitacao") Optional<SolicitacaoServico> solicitacao, Pageable pageRequest);
		
		//busca por cliente
		@Transactional(readOnly=true)
		@Query("SELECT DISTINCT obj FROM Orcamento obj INNER JOIN obj.cliente cli WHERE cli IN :cliente")
		Page<Orcamento> searchByClient(@Param("cliente") Optional<Cliente> cliente, Pageable pageRequest);
		
		
		//find por prestador
		@Transactional(readOnly=true)
		@Query("SELECT DISTINCT obj FROM Orcamento obj INNER JOIN obj.prestador prest WHERE prest IN :prestador")
		Page<Orcamento> searchByPrestador(@Param("prestador") Optional<Prestador> prestador, Pageable pageRequest);
		
}
