package com.appServices.AppServices.domain.enums;

public enum TipoPerfil {
	CLIENTE(1,"ROLE_CLIENTE"),
	PRESTADOR(2,"ROLE_PRESTADOR"),
	ADMIN(3,"ROLE_ADMIN");

	
	private Integer codigo;
	private String nome;
	
	private TipoPerfil(Integer codigo, String nome){
		this.codigo = codigo;
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}


	public String getNome() {
		return nome;
	}

	
	public  static TipoPerfil toEnum(Integer cod){
		if(cod==null) {
			return null;
		}
		for(TipoPerfil x : TipoPerfil.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id inválido"+cod);	
	}
	
	
	
}


