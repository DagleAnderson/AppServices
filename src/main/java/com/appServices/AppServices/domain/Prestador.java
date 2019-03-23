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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Prestador implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String  nomeFantasia;	
	
	private String slogan;
	
	private String localAtendimento;
	
	@OneToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="prestador")
	private EnderecoPrestador endereco;
	
	@OneToOne
	@JoinColumn(name="profissao_id")
	private Profissao profissao;
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="prestador")
	private Curriculo curriculo;
	
	@JsonIgnore
	@OneToMany(mappedBy ="prestador")
	private List<Orcamento> orcamentos = new ArrayList<>();

	@OneToMany(cascade=CascadeType.ALL,mappedBy = "prestador")
	private List<Avaliacoes> avaliacoes = new ArrayList<>();
	
	private Double mediaDeAvaliacao;

	public Prestador() {
		
	}

	public Prestador(Integer id,String nomeFantasia,String slogan, String localAtendimento, Cliente cliente,Profissao profissao) {
		this.id = id;

		this.nomeFantasia = nomeFantasia;
		this.slogan = slogan;
		this.localAtendimento = localAtendimento;
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
		Prestador other = (Prestador) obj;
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
	
	

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getLocalAtendimento() {
		return localAtendimento;
	}

	public void setLocalAtendimento(String localAtendimento) {
		this.localAtendimento = localAtendimento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EnderecoPrestador getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoPrestador endereco) {
		this.endereco = endereco;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao tipoServico) {
		this.profissao= tipoServico;
	}

	public Curriculo getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}
	
	

	public List<Orcamento> getOrcamentos() {
		return orcamentos;
	}

	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}

	public List<Avaliacoes> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacoes> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  this.getCliente().getEmail();
	}

	
	
	public Double getMediaDeAvaliacao() {
		double valor = 0;
		
		for(int i=0; i < this.avaliacoes.size();i++) {
			Avaliacoes aval = this.avaliacoes.get(i);
			valor =  valor + aval.getEstrelas(); 
		}
		
		
		return valor/ this.avaliacoes.size(); 
	}
	
	
	

	
}
