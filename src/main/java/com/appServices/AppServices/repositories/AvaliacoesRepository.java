package com.appServices.AppServices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appServices.AppServices.domain.Avaliacao;

@Repository
public interface AvaliacoesRepository extends JpaRepository<Avaliacao, Integer>  {

}
