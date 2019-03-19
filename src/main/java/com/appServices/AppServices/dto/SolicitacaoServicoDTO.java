package com.appServices.AppServices.dto;

import java.io.Serializable;
import java.util.Date;

import com.appServices.AppServices.domain.SolicitacaoServico;

public class SolicitacaoServicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String produtoServico;
	private String profissao;
	private Date data;
	
	public SolicitacaoServicoDTO() {
		
	}
	
	public SolicitacaoServicoDTO(SolicitacaoServico objDTO) {
		this.id=objDTO.getId();
		this.produtoServico = objDTO.getProdutoServico();
		this.profissao = objDTO.getProfissao().getNome();
		this.data = objDTO.getData();
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


	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
	
}
