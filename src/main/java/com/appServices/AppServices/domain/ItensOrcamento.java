package com.appServices.AppServices.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.appServices.AppServices.domain.enums.StatusSolicitacao;
import com.appServices.AppServices.domain.enums.TipoUnidade;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItensOrcamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String item;
	private Double quantidade;
	private Integer unidade;
	private Double desconto;
	private Double valor;
	
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="orcamento_id")
	private Orcamento orcamento; 
		
	public ItensOrcamento(){
		
	}
	
	public ItensOrcamento(Integer id, String item,Double quantidade,TipoUnidade unidade, Double desconto, Double valor, Orcamento orcamento) {
		this.id = id;
		this.item = item;
		this.quantidade = quantidade;
		this.unidade = unidade.getCodigo();
		this.desconto =desconto;
		this.valor = valor;
		this.orcamento = orcamento;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensOrcamento other = (ItensOrcamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	

	public TipoUnidade getUnidade() {
		return TipoUnidade.toEnum(this.unidade);
	}

	public void setUnidade(TipoUnidade unidade) {
		this.unidade = unidade.getCodigo();
	}
	
	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	} 
	
	
	public double getSubTotal(){
		return (valor - desconto) * quantidade;
	}
	
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append("item:");
		builder.append(getItem()+"\n");
		builder.append("quantidade :");
		builder.append(getQuantidade()+"\n");
		builder.append("desconto :");
		builder.append(nf.format(getDesconto())+"\n");
		builder.append("Valor :");
		builder.append(nf.format(getValor())+"\n");
		
		return builder.toString();
	}
	
	
	
	
	
	
}
