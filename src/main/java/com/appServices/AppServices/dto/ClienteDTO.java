package com.appServices.AppServices.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.appServices.AppServices.Services.Validation.ClienteUpdate;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.enums.TipoPessoa;
import com.appServices.AppServices.domain.enums.TipoSexo;
import com.fasterxml.jackson.annotation.JsonFormat;

@ClienteUpdate
public class ClienteDTO  implements Serializable {
	private static final long serialVersionUID = 1L;

		private Integer id;
		
		@NotEmpty(message="Preenchimento Obrigatório!")
		@Length(min=5 , max=15,message=" O tamanho deve ser entre 5 e 15 caracteres")
		private String nome;
		@NotEmpty(message="Preenchimento Obrigatório!")
		@Length(min=5 , max=20,message=" O tamanho deve ser entre 5 e 15 caracteres")
		private String sobrenome;
		@JsonFormat(pattern="dd/MM/yyyy")
		private Date dataNascimento;
	

		@Length(min=10 , max=10,message=" Seu RG deve conter no mínimo 10 caracteres")
		private String rg;

		@Length(min=11 , max=11,message=" Seu CPF deve conter no mínimo 11 caracteres")
		private String cpfOuCnpj;

		private Integer tipoPessoa;

		private Integer sexo;

		private Set<String> telefones =  new HashSet<>();
		

		//private String login;
		

		private String senha;
		

		@Email(message = "Email inválido")
		private String email;
		
		private Integer prestador;
		
		
		public ClienteDTO() {
			
		}
		
		public ClienteDTO(Cliente clienteObj){
			this.id = clienteObj.getId();
			this.nome = clienteObj.getNome();
			this.sobrenome = clienteObj.getSobrenome();
			this.dataNascimento =clienteObj.getDataNascimento();
			this.rg = clienteObj.getRg();
			this.cpfOuCnpj = clienteObj.getCpfOuCnpj();
			this.tipoPessoa = clienteObj.getTipoPessoa().getCod();
			this.sexo = clienteObj.getSexo().getCod();
			this.senha = clienteObj.getSenha();
			this.email = clienteObj.getEmail();
			this.prestador = clienteObj.getPrestador().getId();
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
		public String getcpfOuCnpj() {
			return cpfOuCnpj;
		}
		public void setcpfOuCnpj(String cpf) {
			this.cpfOuCnpj= cpf;
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
			this.sexo =  sexo;
		}

		public Set<String> getTelefones() {
			return telefones;
		}


		public void setTelefones(Set<String> telefones) {
			this.telefones = telefones;
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

		public Integer getPrestador() {
			return prestador;
		}

		public void setPrestador(Integer prestador) {
			this.prestador = prestador;
		}
		
		
		
}
