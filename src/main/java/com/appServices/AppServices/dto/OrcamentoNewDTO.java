package com.appServices.AppServices.dto;

import java.util.ArrayList;
import java.util.List;

public class OrcamentoNewDTO {
	private Integer id;
	private String produtoServico;
	private Integer prestador;
	private Integer cliente;
	private List<String> itemOrcamento = new ArrayList<>();
	private List<Double> quantidade = new ArrayList<>();
	private List<Double> descontoItem = new ArrayList<>();
	private List<Double> valorItem =new ArrayList<>();
	private Double desconto;
	private Double total;
	private Integer situacao;
	private Integer solicitacaoServico;
	
	public OrcamentoNewDTO() {
		
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

	public Integer getPrestador() {
		return prestador;
	}

	public void setPrestador(Integer prestador) {
		this.prestador = prestador;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	
	
	public List<String> getItemOrcamento() {
		return itemOrcamento;
	}


	public void setItemOrcamento(List<String> itemOrcamento) {
		this.itemOrcamento = itemOrcamento;
	}


	public List<Double> getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(List<Double> quantidade) {
		this.quantidade = quantidade;
	}



	public List<Double> getValorItem() {
		return valorItem;
	}


	public void setValorItem(List<Double> valorItem) {
		this.valorItem = valorItem;
	}


	public List<Double> getDescontoItem() {
		return descontoItem;
	}


	public void setDescontoItem(List<Double> descontoItem) {
		this.descontoItem = descontoItem;
	}

	

	public Double getDesconto() {
		return desconto;
	}


	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}


	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}


	public Integer getSolicitacaoServico() {
		return solicitacaoServico;
	}


	public void setSolicitacaoServico(Integer solicitacaoServico) {
		this.solicitacaoServico = solicitacaoServico;
	}
	
	
	
}
