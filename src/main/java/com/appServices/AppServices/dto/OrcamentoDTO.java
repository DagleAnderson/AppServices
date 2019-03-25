package com.appServices.AppServices.dto;

import java.util.Date;

import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.enums.TipoSituacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrcamentoDTO {
	private Integer id;
	private String produtoServico;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	
	@JsonIgnore
	private Integer prestadorId;
	
	private String prestador;
	
	@JsonIgnore
	private Integer cliente;
	
	private Double desconto;
	private Double total;
	private Integer situacao;
	
	
	public OrcamentoDTO() {
		
	}
	
	public OrcamentoDTO(Orcamento obj) {
		this.id = obj.getId();
		this.produtoServico = obj.getProdutoServico();
		this.prestadorId = obj.getPrestador().getId();
		this.prestador = obj.getPrestador().getNomeFantasia();
		this.cliente = obj.getCliente().getId();
		this.desconto = obj.getDesconto();
		this.total = obj.getTotal();
		this.situacao = obj.getSituacao().getCodigo();
		
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

	public Integer getPrestadorId() {
		return prestadorId;
	}

	public void setPrestadorId(Integer prestadorId) {
		this.prestadorId = prestadorId;
	}

	
	public String getPrestador() {
		return prestador;
	}

	public void setPrestador(String prestador) {
		this.prestador = prestador;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getTotal(){
		return  total;
	}

	public TipoSituacao getSituacao() {
		return TipoSituacao.toEnum(situacao);
	}

	public void setSituacao(TipoSituacao situacao) {
		this.situacao = situacao.getCodigo();
	}


}
