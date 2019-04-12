package com.appServices.AppServices.domain.enums;

public enum StatusAtendimento {
	PENDENTE(1,"PENDENTE"),
	CONFIRMADO(2,"CONFIRMADO"),
	REALIZADO(3,"REALIZADO"),
	CANCELADO(4,"CANCELADO");
	
	private Integer cod;
	private String nome;
	
	
	private StatusAtendimento(Integer codigo, String nome){
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
	
	public static StatusAtendimento toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		for(StatusAtendimento x : StatusAtendimento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id inválido" + cod);	
	}
	
	
	
}
