package com.appServices.AppServices.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class SolicitacaoServico implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String produtoServico;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private  Cliente cliente;
	
	@OneToMany(mappedBy ="solicitacao",cascade = CascadeType.ALL)
	private List<ItensSolicitacao> itemServico = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="profissao_id")
	private Profissao profissao;
	
	@OneToMany(mappedBy="solicitacao")
	private List<Orcamento> orcamento = new ArrayList<>();
	
	public SolicitacaoServico() {
		
	}
	
	public SolicitacaoServico(Integer id, String produtoServico, Cliente cliente, Profissao profissao) {
		this.id = id;
		this.produtoServico = produtoServico;
		this.cliente = cliente;
		this.profissao = profissao;
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
		SolicitacaoServico other = (SolicitacaoServico) obj;
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

	public String getProdutoServico() {
		return produtoServico;
	}

	public void setProdutoServico(String produtoServico) {
		this.produtoServico = produtoServico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItensSolicitacao> getItemServico() {
		return itemServico;
	}

	public void setItemServico(List<ItensSolicitacao> itemServico) {
		this.itemServico = itemServico;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}
	

	public List<Orcamento> getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(List<Orcamento> orcamento) {
		this.orcamento = orcamento;
	}
	
	
	
	
	
}
