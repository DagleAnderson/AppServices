package com.appServices.AppServices.domain.enums;

public enum StatusSolicitacao {
	ABERTA(1,"ABERTA"),
	FECHADA(2,"FECHADA");
	
	private Integer cod;
	private String nome;
	
	
	private StatusSolicitacao(Integer codigo, String nome){
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
	
	public static StatusSolicitacao toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		for(StatusSolicitacao x : StatusSolicitacao.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id inválido" + cod);	
	}
	
	
	
	
}
