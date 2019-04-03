package com.appServices.AppServices.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.appServices.AppServices.domain.FormaDePagamento;


@Repository
public interface PagamentoRepository extends JpaRepository<FormaDePagamento, Integer>  {
	

}
