package com.appServices.AppServices.domain.enums;

public enum TipoUnidade {
	UN(1,"UNIDADE"),
	MT(2,"METRO"),
	KG(3,"KILO"),
	LT(4,"LITRO");
	
	private Integer codigo;
	private String nome;
	
	private TipoUnidade(Integer codigo, String nome){
		this.codigo = codigo;
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}


	public String getNome() {
		return nome;
	}

	
	public  static TipoUnidade toEnum(Integer cod){
		if(cod==null) {
			return null;
		}
		for(TipoUnidade x : TipoUnidade.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id inválido"+cod);	
	}
	
}
