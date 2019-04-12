package com.appServices.AppServices.domain.enums;

public enum TipoSituacao {
	PENDENTE(1,"PENDENTE"),
	ANALISE( 2,"ANALISE"),
	APROVADO(3,"APROVADO"),
	REJEITADO(4,"REJEITADO");
	
	private Integer cod;
	private String nome;
	
	
	private TipoSituacao(Integer codigo, String nome){
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
	
	public static TipoSituacao toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		for(TipoSituacao x : TipoSituacao.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id inválido" + cod);	
	}
	
	
	
}


