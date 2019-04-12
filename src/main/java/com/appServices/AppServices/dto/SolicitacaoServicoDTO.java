package com.appServices.AppServices.dto;

import java.io.Serializable;
import java.util.Date;

import com.appServices.AppServices.domain.SolicitacaoServico;
import com.appServices.AppServices.domain.enums.StatusSolicitacao;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SolicitacaoServicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String produtoServico;
	private String cliente;
	private String profissao;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	private Integer situacao;
	
	public SolicitacaoServicoDTO() {
		
	}
	
	public SolicitacaoServicoDTO(SolicitacaoServico objDTO) {
		this.id=objDTO.getId();
		this.produtoServico = objDTO.getProdutoServico();
		this.cliente = objDTO.getCliente().getNome() +" "+ objDTO.getCliente().getSobrenome();
		this.profissao = objDTO.getProfissao().getNome();
		this.data = objDTO.getData();
		this.situacao = objDTO.getSituacao().getCod();
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

	

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
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
	
	
	public StatusSolicitacao getSituacao() {
		return StatusSolicitacao.toEnum(situacao);
	}

	public void setSituacao(StatusSolicitacao situacao) {
		this.situacao = situacao.getCod();
	}
	
	
}
