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
import javax.persistence.OneToOne;

import com.appServices.AppServices.domain.enums.TipoSituacao;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Orcamento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String produtoServico;
	
	@ManyToOne
	@JoinColumn(name="prestador_id")
	private Prestador prestador;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy="orcamento",cascade=CascadeType.ALL)
	private List<ItensOrcamento> itensOrcamento = new ArrayList<>(); 
	

	private Double desconto;
	private Integer situacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="solicitacao_id")
	private SolicitacaoServico solicitacao;
	
	@JsonIgnore
	@OneToOne
	private Pedido pedido;
	
	public Orcamento() {
		
	}
	
	public Orcamento(Integer id,String produtoServico, Prestador prestador, Cliente cliente, Double total, Double desc,
			TipoSituacao situacao,SolicitacaoServico solicitacao) {
		this.id = id;
		this.produtoServico = produtoServico;
		this.prestador = prestador;
		this.cliente = cliente;
		this.desconto = desc;
		this.situacao = situacao.getCodigo();
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
		Orcamento other = (Orcamento) obj;
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



	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

	public List<ItensOrcamento> getItensOrcamento() {
		return itensOrcamento;
	}



	public void setItensOrcamento(List<ItensOrcamento> itensOrcamento) {
		this.itensOrcamento = itensOrcamento;
	}


	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desc) {
		this.desconto = desc;
	}

	public TipoSituacao getSituacao() {
		return TipoSituacao.toEnum(situacao);
	}

	public void setSituacao(TipoSituacao situacao) {
		this.situacao = situacao.getCodigo();
	}



	public SolicitacaoServico getSolicitacao() {
		return solicitacao;
	}



	public void setSolicitacao(SolicitacaoServico solicitacao) {
		this.solicitacao = solicitacao;
	}
	
	
	public double getValorTotal() {
		double soma = 0.0;
		
		for(ItensOrcamento itensOrc: itensOrcamento ) {
			soma = soma + itensOrc.getSubTotal();
		}
		
		return soma;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Orçamento:");
		builder.append( getId()+"\n");
		builder.append("Produto:");
		builder.append(getProdutoServico()+"\n");
		builder.append("Cliente=");
		builder.append(getCliente().getNome() +" "+ getCliente().getSobrenome() + "\n");
		builder.append(",Serviço=");
		for(ItensOrcamento is : getItensOrcamento()) {
			builder.append(is.toString()+"\n");
		}	
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
