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
	
	//find pedidos by cliente
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Pedido obj INNER JOIN obj.cliente cli WHERE cli IN :cliente")
	Page<Pedido> searchByClient(@Param("cliente") Optional<Cliente> cliente, Pageable pageRequest);
	
			
	//find pedidos by prestador
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Pedido obj INNER JOIN obj.prestador prest WHERE prest IN :prestador")
	Page<Pedido> searchByPrestador(@Param("prestador") Optional<Prestador> prestador, Pageable pageRequest);
	
}
