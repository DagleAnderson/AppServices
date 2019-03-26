package com.appServices.AppServices.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Profissao;
import com.appServices.AppServices.domain.SolicitacaoServico;

public interface SolicitacaoServicoRepository extends JpaRepository<SolicitacaoServico, Integer> {
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM SolicitacaoServico obj INNER JOIN obj.cliente cli WHERE cli IN :cliente")
	Page<SolicitacaoServico> findByCliente(@Param("cliente") Optional<Cliente> cliente, Pageable pageRequest);
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM SolicitacaoServico obj INNER JOIN obj.profissao prest WHERE prest IN :profissao")
	Page<SolicitacaoServico> findByProfissao(@Param("profissao") Optional<Profissao> profissao, Pageable pageRequest);
	
}
