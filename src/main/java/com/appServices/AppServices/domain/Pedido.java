package com.appServices.AppServices.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.appServices.AppServices.domain.enums.StatusPagamento;
import com.appServices.AppServices.domain.enums.TipoSituacao;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable{
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
	
	@OneToMany(mappedBy="pedido",cascade = CascadeType.ALL)
	private List<ItensPedido> itensPedido = new ArrayList<>();
	private Double desconto;
	private Double total;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	private Integer situacao;
	private Integer statusPagamento;
	
	@OneToOne
	@JoinColumn(name="orcamento_id")
	private Orcamento orcamento;
	
	public Pedido() {
		
	}

	public Pedido(Integer id,String produtoServico, Prestador prestador, Cliente cliente, Double desconto, Date data,TipoSituacao situacao,StatusPagamento statusPg,Orcamento orcamento) {
		this.id = id;
		this.produtoServico = produtoServico;
		this.prestador = prestador;
		this.cliente = cliente;
		this.desconto = desconto;
		this.data = data;
		this.situacao = situacao.getCodigo();
		this.statusPagamento = statusPg.getCod();
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
		Pedido other = (Pedido) obj;
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

	public void setItensPedido(List<ItensPedido> itensPedido) {
		this.itensPedido = itensPedido;
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

	public List<ItensPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItens(List<ItensPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public Double getTotal() {
		return this.getValorTotal();
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public TipoSituacao getSituacao() {
		return TipoSituacao.toEnum(situacao);
	}

	public void setSituacao(TipoSituacao situacao) {
		this.situacao = situacao.getCodigo();
	}
	
	public StatusPagamento getStatusPagamento() {
		return StatusPagamento.toEnum(statusPagamento);
	}

	public void setStatusPagamento(StatusPagamento status) {
		this.statusPagamento = status.getCod();
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	
	

	
	public double getValorTotal() {
		double soma = 0.0;
		
		for(ItensPedido itensPed: itensPedido ) {
			soma = soma + itensPed.getSubTotal();
		}
		soma = soma - this.getDesconto();
		
		this.setTotal(soma);
		
		return soma;
	}
	
	public double getDescontoTotal() {
		double soma = 0.0;
		
		for(ItensPedido itensPed: itensPedido ) {
			soma = soma + itensPed.getDesconto();
		}
		soma = soma + this.getDesconto();
		
	
		return soma;
	}
	

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append("Olá "+getCliente().getNome() +" "+ getCliente().getSobrenome()+"!");
		builder.append( "Foi gerado um novo pedido apartir do orcamento Nº");
		builder.append(getOrcamento().getId()+"\n");
		builder.append( "Logo "+ getPrestador().getNomeFantasia());
		builder.append(" entrará em contato com você!"+"\n");
		builder.append("\n");
		builder.append("-------------- **    Pedido Nº #"+getId()+"   ** ----------------"+"\n");
		builder.append("\n");
		builder.append("Data e hora: ");
		builder.append(getData()+"\n");
		builder.append("\n");
		builder.append("Orçamento Efetivado Nº :");
		builder.append(getOrcamento().getId()+"\n");
		builder.append( "Prestador :");
		builder.append(getPrestador().getNomeFantasia()+"\n");
		builder.append("\n");
		builder.append("------------- **   Itens do Pedido   ** ---------------"+"\n");
		builder.append("\n");
				for(ItensPedido itens : getItensPedido()) {
					builder.append(itens.toString()+"\n");
				}
		builder.append("----------- **************************** -------------"+"\n");
		builder.append("Desconto geral :");
		builder.append(nf.format(getDesconto())+"\n"+"\n");		
		
		builder.append("------------------------- **  TOTAIS  ** --------------------------");
		builder.append("\n");
		builder.append("Total de desconto :");
		builder.append(nf.format(getDescontoTotal())+"\n");
		builder.append("Total do Orçamento :");
		builder.append(nf.format(getValorTotal())+"\n");
		
		return builder.toString();
	}
	
	
	
	
	
	
}
