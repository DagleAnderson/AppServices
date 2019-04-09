package com.appServices.AppServices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appServices.AppServices.domain.PagSeguro;

@Repository
public interface PagSeguroRepository extends JpaRepository<PagSeguro, Integer>  {

}
