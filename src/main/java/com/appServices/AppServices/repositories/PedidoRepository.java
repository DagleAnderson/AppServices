package com.appServices.AppServices.repositories;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.Pedido;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.dto.PedidoDTO;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Transactional(readOnly=true)	
	Page<PedidoDTO> findByCliente(Cliente cliente,Pageable pageRequest);
	
	//busca por cliente
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Pedido obj INNER JOIN obj.cliente cli WHERE cli IN :cliente")
	Page<Pedido> searchByClient(@Param("cliente") Optional<Cliente> cliente, Pageable pageRequest);
	
	
	// busca por cliente e situação - {pedidos a serem exidas no perfil de cliente}
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Pedido obj INNER JOIN obj.cliente cli WHERE cli =:cliente AND obj.atendimento =:status")
	Page<Pedido> findByClienteAndSituacao(
			@Param("cliente") Optional<Cliente> cliente,
			@Param("status") Integer status, 
			Pageable pageRequest);
	
	
	
			
	//busca por prestador
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Pedido obj INNER JOIN obj.prestador prest WHERE prest IN :prestador")
	Page<Pedido> searchByPrestador(@Param("prestador") Optional<Prestador> prestador, Pageable pageRequest);
	
	
	// busca por prestador e situação - {pedidos a serem exidas no perfil de prestador}
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Pedido obj INNER JOIN obj.prestador prest WHERE prest =:prestador AND obj.atendimento =:status")
	Page<Pedido> findByPrestadorAndSituacao(
			@Param("prestador") Optional<Prestador> prestador,
			@Param("status") Integer status, 
			Pageable pageRequest);

	
}
