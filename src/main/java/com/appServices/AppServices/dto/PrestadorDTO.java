package com.appServices.AppServices.dto;


import java.io.Serializable;

import com.appServices.AppServices.domain.Prestador;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PrestadorDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nomeFantasia;
	private String slogan;
	private String localAtendimento;
	
	//Endereço
	private String cidade;
	private String estado;
	private String cep;
	private String bairro;
	private String rua;
	private int numero;
	private String complemento;
	
	
	@JsonIgnore
	private Integer clienteId;
	private String clienteNome;
	
	@JsonIgnore
	private Integer profissaoId;
	private String profissaoNome;
	
	
	public PrestadorDTO() {
		
	}
	
	public PrestadorDTO(Prestador prestadorObj ) {
		this.id = prestadorObj.getId();
		this.nomeFantasia = prestadorObj.getNomeFantasia();
		this.slogan =prestadorObj.getSlogan();
		this.localAtendimento = prestadorObj.getLocalAtendimento();
		
		this.cidade = prestadorObj.getEndereco().getCidade();
		this.estado = prestadorObj.getEndereco().getEstado();
		this.cep = prestadorObj.getEndereco().getCep();
		this.bairro = prestadorObj.getEndereco().getBairro();
		this.rua = prestadorObj.getEndereco().getRua();
		this.numero = prestadorObj.getEndereco().getNumero();
		this.complemento = prestadorObj.getEndereco().getComplemento();
		
		this.clienteId = prestadorObj.getCliente().getId();
		this.clienteNome = prestadorObj.getCliente().getNome();
		this.profissaoId = prestadorObj.getProfissao().getId();
		this.profissaoNome = prestadorObj.getProfissao().getNome();
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

	
	
	//Endereço do prestador
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	
	
	
	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	
	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}


	public Integer getProfissaoId() {
		return profissaoId;
	}

	public void setProfissaoId(Integer profissaoId) {
		this.profissaoId = profissaoId;
	}


	public String getProfissaoNome() {
		return profissaoNome;
	}

	public void setProfissaoNome(String profissaoNome) {
		this.profissaoNome = profissaoNome;
	}
	
	

	
		
}
