package com.appServices.AppServices.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PrestadorNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String  nomeFantasia;	
	private String slogan;
	private String localAtendimento;
	
	@NotEmpty(message="informe sua profissao!")
	private Integer profissao;
	
	@NotEmpty(message="usuario nao identificado")
	private Integer cliente;
	
	private String email;

	@NotEmpty(message="Preenchimento obrigatório")
	private String cidade;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String estado;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cep;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String bairro;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String rua;
	
	@NotNull(message="preenchimento obrigatório")
	private int numero;

	private String complemento;
	

	

	public PrestadorNewDTO() {
		
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


	public Integer getProfissao() {
		return profissao;
	}


	public void setProfissao(Integer profissao) {
		this.profissao = profissao;
	}

	
	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	
	
}
