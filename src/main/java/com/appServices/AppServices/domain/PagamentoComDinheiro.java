package com.appServices.AppServices.domain;


import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComDinheiro")
public class PagamentoComDinheiro extends FormaDePagamento{
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComDinheiro() {
		
	}

	public PagamentoComDinheiro(Integer id,Orcamento orcamento,Integer numeroDeParcelas) {
		super(id,orcamento);
		this.numeroDeParcelas = numeroDeParcelas;

	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	

}
