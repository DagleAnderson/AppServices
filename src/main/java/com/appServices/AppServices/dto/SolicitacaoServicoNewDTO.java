package com.appServices.AppServices.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SolicitacaoServicoNewDTO {
	private Integer id;
	private String produtoServico;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	private Integer cliente;
	private String itemSolicitacao1;
	private String itemSolicitacao2;
	private String itemSolicitacao3;
	private Integer profissao;
	private Integer statusSolicitacao;
	

	public SolicitacaoServicoNewDTO() {
		
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
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setProdutoServico(String produtoServico) {
		this.produtoServico = produtoServico;
	}
	public Integer getCliente(){
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public String getItemSolicitacao1() {
		return itemSolicitacao1;
	}
	public void setItemSolicitacao1(String itemSolicitacao1) {
		this.itemSolicitacao1 = itemSolicitacao1;
	}
	public String getItemSolicitacao2() {
		return itemSolicitacao2;
	}
	public void setItemSolicitacao2(String itemSolicitacao2) {
		this.itemSolicitacao2 = itemSolicitacao2;
	}
	public String getItemSolicitacao3() {
		return itemSolicitacao3;
	}
	public void setItemSolicitacao3(String itemSolicitacao3) {
		this.itemSolicitacao3 = itemSolicitacao3;
	}
	public Integer getProfissao() {
		return profissao;
	}
	public void setProfissao(Integer profissao) {
		this.profissao = profissao;
	}
	
	public Integer getStatusSolicitacao() {
		return statusSolicitacao;
	}

	public void setStatusSolicitacao(Integer statusSolicitacao) {
		this.statusSolicitacao = statusSolicitacao;
	}
	
	
}
