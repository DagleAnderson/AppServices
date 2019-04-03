package com.appServices.AppServices.domain.enums;

public enum TipoPagamento {
	DI(1,"DINHEIRO"),
	CC(2,"CARTÃO DE CRÉDITO"),
	CD(3,"CARTÃO DE DÉBITO");
	
	private Integer codigo;
	private String nome;
	
	private TipoPagamento(Integer codigo, String nome){
		this.codigo = codigo;
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}


	public String getNome() {
		return nome;
	}

	
	public  static TipoPagamento toEnum(Integer cod){
		if(cod==null) {
			return null;
		}
		for(TipoPagamento x : TipoPagamento.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id inválido"+cod);	
	}
	
}
