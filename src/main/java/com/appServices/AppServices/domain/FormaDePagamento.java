package com.appServices.AppServices.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class FormaDePagamento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="orcamento_id")
	@MapsId
	private Orcamento orcamento;
	
	
	public FormaDePagamento() {
		
	}


	public FormaDePagamento(Integer id, Orcamento orcamento) {
		this.id = id;
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
		FormaDePagamento other = (FormaDePagamento) obj;
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

	

	public Orcamento getOrcamento() {
		return orcamento;
	}


	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	
	
	
	
	
}
