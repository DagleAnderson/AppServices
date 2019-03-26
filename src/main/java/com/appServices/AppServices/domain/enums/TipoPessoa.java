package com.appServices.AppServices.domain.enums;

public enum TipoPessoa {
	
	FISICA(1,"PF"), 
	JURIDICA(2,"PJ");
	
	private Integer cod;
	private String nome;

	private TipoPessoa(Integer codigo, String nome){
		this.cod = codigo;
		this.nome=nome;
	}

	public Integer getCod() {
		return this.cod;
	}


	public String getNome() {
		return this.nome;
	}

	
	public static TipoPessoa toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		for(TipoPessoa x : TipoPessoa.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id inválido"+cod);	
	}
	
}
