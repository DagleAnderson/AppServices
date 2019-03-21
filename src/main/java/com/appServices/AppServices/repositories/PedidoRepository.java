package com.appServices.AppServices.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Pedido;
import com.appServices.AppServices.dto.PedidoDTO;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Transactional(readOnly=true)	
	Page<PedidoDTO> findByCliente(Cliente cliente,Pageable pageRequest);
}
