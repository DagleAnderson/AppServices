package com.appServices.AppServices.domain.enums;

public enum TipoSexo {
		MASCULINO(1,"MASCULINO"),
		FEMININO(2,"FEMININO");
		
		private Integer cod;
		private String nome;
		
		private TipoSexo(Integer codigo, String nome){
			this.cod = codigo;
			this.nome = nome;
		}

		public Integer getCod() {
			return this.cod;
		}
		
		public String getNome() {
			return this.nome;
		}

		
		public  static TipoSexo toEnum(Integer cod){
			if(cod==null) {
				return null;
			}
			for(TipoSexo x : TipoSexo.values()) {
				if(cod.equals(x.getCod())) {
					return x;
				}
			}
			
			throw new IllegalArgumentException("id inválido"+cod);	
		}

}