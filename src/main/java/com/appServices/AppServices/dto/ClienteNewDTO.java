package com.appServices.AppServices.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.appServices.AppServices.Services.Validation.ClienteInsert;
import com.fasterxml.jackson.annotation.JsonFormat;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório") 	
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String sobrenome;
	
	@NotNull(message="preenchimento obrigatório")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataNascimento;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String rg;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cpfOuCnpj;
	
	@NotNull(message="selecione o tipo de pessoa")
	private Integer tipoPessoa;
	
	@NotNull(message="selecione o sexo")
	private Integer sexo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String telefone1;
	
	private String telefone2;
	
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
	
	
	//@NotEmpty(message="Preenchimento Obrigatório!")
	//private String login;
	
	@NotEmpty(message="Preenchimento Obrigatório!")
	private String senha;
	
	@NotEmpty(message="Preenchimento Obrigatório!")
	private String email;
	
	
	
	public ClienteNewDTO() {
		
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSobrenome() {
		return sobrenome;
	}


	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}


	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}


	public Integer getTipoPessoa() {
		return tipoPessoa;
	}


	public void setTipoPessoa(Integer tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}


	public Integer getSexo() {
		return sexo;
	}


	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}


	public String getTelefone1() {
		return telefone1;
	}


	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}


	public String getTelefone2() {
		return telefone2;
	}


	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
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


	/**public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}**/


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
