package com.appServices.AppServices.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PedidoNewDTO {
	private Integer id;
	private String produtoServico;
	private Integer prestador;
	private Integer cliente;
	private List<String> itemPedido = new ArrayList<>();
	private List<Double> valorItem = new ArrayList<>();
	private List<Double> quantidade = new ArrayList<>();
	private List<Double> descontoItem = new ArrayList<>();
	private Double desconto;
	private Double total;
	private Date data;
	private Integer situacao;
	private Integer statusPagamento;
	private Integer orcamento;
	
	public PedidoNewDTO() {
		
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

	
	public List<String> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(List<String> itemPedido) {
		this.itemPedido = itemPedido;
	}

	public List<Double> getValorItem() {
		return valorItem;
	}

	public void setValorItem(List<Double> valorItem) {
		this.valorItem = valorItem;
	}

	public List<Double> getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(List<Double> quantidade) {
		this.quantidade = quantidade;
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

	
	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}
	
	public Integer getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(Integer statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public Integer getOrcamento() {
		return orcamento;
	}


	public void setOrcamento(Integer orcamento) {
		this.orcamento = orcamento;
	}



}
