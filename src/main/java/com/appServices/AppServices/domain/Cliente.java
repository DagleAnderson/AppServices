package com.appServices.AppServices.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.appServices.AppServices.domain.enums.TipoPerfil;
import com.appServices.AppServices.domain.enums.TipoPessoa;
import com.appServices.AppServices.domain.enums.TipoSexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String sobrenome;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataNascimento;
	private String rg;
	private String cpfOuCnpj;
	private Integer tipoPessoa;
	private Integer sexo;
	
	private String login;
	
	@JsonIgnore
	private String senha;
	
	@Column(unique=true)
	private String email;
	

	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones =  new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@OneToOne(mappedBy="cliente",cascade=CascadeType.ALL)
	private EnderecoCliente endereco;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente",cascade=CascadeType.ALL)
	private List<Avaliacoes> avaliacoes = new ArrayList<>();
	
	@OneToOne(mappedBy = "cliente",cascade=CascadeType.ALL)
	private Prestador prestador;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente",cascade=CascadeType.ALL)
	private List<SolicitacaoServico> solicitacao = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente",cascade=CascadeType.ALL)
	private List<Pedido> pedido = new ArrayList<>();
	

	@JsonIgnore
	@OneToMany(mappedBy = "cliente",cascade=CascadeType.ALL)
	private List<Orcamento> orcamento = new ArrayList<>();
	
	
	public Cliente() {
		addPerfil(TipoPerfil.CLIENTE);
	}
	

	public Cliente(Integer id, String nome,String sobrenome, Date dataNascimento, String rg,String CpfOuCnpj, TipoPessoa tipoPessoa, TipoSexo sexo,String login,String senha,String email) {
		this.id = id;
		this.nome = nome;
		this.sobrenome=sobrenome;
		this.dataNascimento = dataNascimento;
		this.rg = rg;
		this.cpfOuCnpj=CpfOuCnpj;
		this.tipoPessoa =tipoPessoa.getCod();
		this.sexo = sexo.getCod();
		this.login = login;
		this.senha = senha;
		this.email = email;
		addPerfil(TipoPerfil.CLIENTE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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


	public TipoPessoa getTipoPessoa() {
		return TipoPessoa.toEnum(tipoPessoa);
	}
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa.getCod();
	}
	public TipoSexo getSexo() {
		return TipoSexo.toEnum(sexo);
	}
	public void setSexo(TipoSexo sexo) {
		this.sexo =  sexo.getCod();
	}


	public Set<String> getTelefones() {
		return telefones;
	}


	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	

	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<TipoPerfil> getPerfis() {
		return perfis.stream().map(x -> TipoPerfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(TipoPerfil perfil) {
		perfis.add(perfil.getCodigo());
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public EnderecoCliente getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoCliente endereco) {
		this.endereco = endereco;
	}
	

}
