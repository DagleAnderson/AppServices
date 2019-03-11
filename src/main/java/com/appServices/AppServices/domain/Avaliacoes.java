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
public class Avaliacoes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="prestador_id")
	private Prestador prestador;
	
	private Double estrelas;
	private String comentario;
	
	
	public Avaliacoes() {
		
	}


	public Avaliacoes(Integer id, Cliente cliente, Prestador prestador, Double estrelas, String comentario) {
		this.id = id;
		this.cliente = cliente;
		this.prestador = prestador;
		this.estrelas = estrelas;
		this.comentario = comentario;
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
		Avaliacoes other = (Avaliacoes) obj;
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


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Prestador getPrestador() {
		return prestador;
	}


	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}


	public Double getEstrelas() {
		return estrelas;
	}


	public void setEstrelas(Double estrelas) {
		this.estrelas = estrelas;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
}

