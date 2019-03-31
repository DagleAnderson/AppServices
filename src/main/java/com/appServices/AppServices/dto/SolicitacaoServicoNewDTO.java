package com.appServices.AppServices.dto;

import java.util.Date;

import com.appServices.AppServices.domain.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SolicitacaoServicoNewDTO {
	private Integer id;
	private String produtoServico;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	private Cliente cliente;
	private String itemSolicitacao1;
	private String itemSolicitacao2;
	private String itemSolicitacao3;
	private String itemSolicitacao4;
	private String itemSolicitacao5;
	private String itemSolicitacao6;
	private String itemSolicitacao7;
	private String itemSolicitacao8;

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
	public Cliente getCliente(){
		return cliente;
	}
	public void setCliente(Cliente cliente) {
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
	
	
	public String getItemSolicitacao4() {
		return itemSolicitacao4;
	}

	public void setItemSolicitacao4(String itemSolicitacao4) {
		this.itemSolicitacao4 = itemSolicitacao4;
	}

	public String getItemSolicitacao5() {
		return itemSolicitacao5;
	}

	public void setItemSolicitacao5(String itemSolicitacao5) {
		this.itemSolicitacao5 = itemSolicitacao5;
	}

	public String getItemSolicitacao6() {
		return itemSolicitacao6;
	}

	public void setItemSolicitacao6(String itemSolicitacao6) {
		this.itemSolicitacao6 = itemSolicitacao6;
	}

	public String getItemSolicitacao7() {
		return itemSolicitacao7;
	}

	public void setItemSolicitacao7(String itemSolicitacao7) {
		this.itemSolicitacao7 = itemSolicitacao7;
	}

	public String getItemSolicitacao8() {
		return itemSolicitacao8;
	}

	public void setItemSolicitacao8(String itemSolicitacao8) {
		this.itemSolicitacao8 = itemSolicitacao8;
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
