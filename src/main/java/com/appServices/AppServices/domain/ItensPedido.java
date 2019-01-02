package com.appServices.AppServices.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItensPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String item;
	private Double valor;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
	
	public ItensPedido() {
	}

	public ItensPedido(Integer id, String item, Double valor, Pedido pedido) {
		this.id = id;
		this.item = item;
		this.valor = valor;
		this.pedido = pedido;
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
		ItensPedido other = (ItensPedido) obj;
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

	public String getDescricao() {
		return item;
	}

	public void setDescricao(String item) {
		this.item = item;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("item=");
		builder.append(getDescricao());
		builder.append("\n");
		builder.append("valor=");
		builder.append(getValor());
		builder.append("\n");
		return builder.toString();
	}
	
	
	
	
}
