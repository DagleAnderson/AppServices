package com.appServices.AppServices.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.Profissao;
import com.appServices.AppServices.domain.SolicitacaoServico;
import com.appServices.AppServices.domain.enums.StatusSolicitacao;
import com.appServices.AppServices.domain.enums.TipoSituacao;

public interface SolicitacaoServicoRepository extends JpaRepository<SolicitacaoServico, Integer> {
	
	
	// busca por cliente - {solicitações a serem exidas no perfil de cliente}
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM SolicitacaoServico obj INNER JOIN obj.cliente cli WHERE cli IN :cliente")
	Page<SolicitacaoServico> findByCliente(@Param("cliente") Optional<Cliente> cliente, Pageable pageRequest);
	
	
	// busca por cliente e situação - {solicitações a serem exidas no perfil de cliente}
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM SolicitacaoServico obj INNER JOIN obj.cliente cli WHERE cli =:cliente AND obj.situacao =:status")
	Page<SolicitacaoServico> findByClienteAndSituacao(
			@Param("cliente") Optional<Cliente> cliente,
			@Param("status") Integer status, 
			Pageable pageRequest);
	
	
	
	//busca por profissão - {solicitações a serem exibidas para o prestador de determinada profissão}
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM SolicitacaoServico obj INNER JOIN obj.profissao prof WHERE prof IN :profissao")
	Page<SolicitacaoServico> findByProfissao(@Param("profissao") Optional<Profissao> profissao, Pageable pageRequest);
	
	

	
	// busca por prestador e situação - {solicitações a serem exidas no perfil de prestador}
		@Transactional(readOnly=true)
		@Query("SELECT DISTINCT obj FROM SolicitacaoServico obj INNER JOIN obj.profissao prof WHERE prof =:profissao AND obj.situacao =:status")
		Page<SolicitacaoServico> findByProfissaoAndSituacao(
				@Param("profissao") Optional<Profissao> profissao,
				@Param("status") Integer status, 
				Pageable pageRequest);


}
