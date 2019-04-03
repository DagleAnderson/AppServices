package com.appServices.AppServices.domain;


import javax.persistence.Entity;

import com.appServices.AppServices.domain.enums.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComDinheiro")
public class PagamentoComDinheiro extends FormaDePagamento{
	private static final long serialVersionUID = 1L;
	
	private Integer tipoPagamento;
	
	public PagamentoComDinheiro() {
		
	}

	public PagamentoComDinheiro(Integer id,Orcamento orcamento) {
		super(id,orcamento);
		this.tipoPagamento = 1;
		// TODO Auto-generated constructor stub
	}
	
	

	public TipoPagamento getTipoPagamento() {
		return TipoPagamento.toEnum(this.tipoPagamento);
	}


	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento.getCodigo();
	}


}
