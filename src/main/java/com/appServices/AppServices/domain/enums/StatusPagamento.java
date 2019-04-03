package com.appServices.AppServices.domain.enums;

public enum StatusPagamento {
	PENDENTE(1,"PENDENTE"),
	FATURADO(2,"FATURADO"),
	CANCELADO(3,"CANCELADO");
	
	private Integer cod;
	private String nome;
	
	
	private StatusPagamento(Integer codigo, String nome){
		this.cod = codigo;
		this.nome = nome;
	}


	public Integer getCod() {
		return cod;
	}


	public void setCod(Integer cod) {
		this.cod = cod;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static StatusPagamento toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		for(StatusPagamento x : StatusPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id inválido" + cod);	
	}
	
	
	
	
}
