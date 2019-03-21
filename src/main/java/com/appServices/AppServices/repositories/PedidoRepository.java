package com.appServices.AppServices.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.Pedido;
import com.appServices.AppServices.dto.PedidoDTO;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Transactional(readOnly=true)	
	Page<PedidoDTO> findByCliente(Cliente cliente,Pageable pageRequest);
	

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Pedido obj INNER JOIN obj.orcamento orca WHERE orca IN :orcamento" )
	Page<Pedido> findByOrcamento(@Param("orcamento") Orcamento orcamento,Pageable pageRequest);
}
