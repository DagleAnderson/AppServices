package com.appServices.AppServices.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.appServices.AppServices.domain.enums.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends FormaDePagamento {
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	private Integer tipoPagamento;
	
	public PagamentoComCartao() {
		
	}

	public PagamentoComCartao(Integer id, Orcamento orcamento,Integer numeroDeParcelas) {
		super(id,orcamento);
		this.numeroDeParcelas = numeroDeParcelas;
		this.tipoPagamento = 2;
		// TODO Auto-generated constructor stub
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	public TipoPagamento getTipoPagamento() {
		return TipoPagamento.toEnum(this.tipoPagamento);
	}


	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento.getCodigo();
	}

	
	
	
}
