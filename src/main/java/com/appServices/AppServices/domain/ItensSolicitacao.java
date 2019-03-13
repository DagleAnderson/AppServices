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
public class ItensSolicitacao  implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="solicitacao_id")
	private SolicitacaoServico solicitacao;
	
	public ItensSolicitacao() {
		
	}
	
	public ItensSolicitacao(Integer id, String descricao,SolicitacaoServico solicitacao) {
		this.id = id;
		this.descricao = descricao;
		this.solicitacao = solicitacao;
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
		ItensSolicitacao other = (ItensSolicitacao) obj;
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
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("produto/Serviço:");
		builder.append(getId());
		builder.append(", Detalhes do item:");
		builder.append(getDescricao());
		return builder.toString();
	}
	
	
	
	
	
	
}
