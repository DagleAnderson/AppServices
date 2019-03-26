package com.appServices.AppServices.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.appServices.AppServices.domain.enums.StatusSolicitacao;
import com.appServices.AppServices.domain.enums.TipoSituacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class SolicitacaoServico implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String produtoServico;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	private Integer statusSolicitacao;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private  Cliente cliente;
	
	@OneToMany(mappedBy ="solicitacao",cascade = CascadeType.ALL)
	private List<ItensSolicitacao> itemServico = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="profissao_id")
	private Profissao profissao;
	
	@OneToMany(mappedBy="solicitacao")
	private List<Orcamento> orcamento = new ArrayList<>();
	
	private int i;
	
	public SolicitacaoServico() {
		
	}
	
	public SolicitacaoServico(Integer id, String produtoServico,Date data, Cliente cliente, Profissao profissao,StatusSolicitacao status) {
		this.id = id;
		this.produtoServico = produtoServico;
		this.data = data;
		this.cliente = cliente;
		this.profissao = profissao;
		this.statusSolicitacao =  status.getCod();
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolicitacaoServico other = (SolicitacaoServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<ItensSolicitacao> getItemServico() {
		return itemServico;
	}

	public void setItemServico(List<ItensSolicitacao> itemServico) {
		this.itemServico = itemServico;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}
	

	public List<Orcamento> getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(List<Orcamento> orcamento) {
		this.orcamento = orcamento;
	}
	
	
	public StatusSolicitacao getStatusSolicitacao() {
		return StatusSolicitacao.toEnum(statusSolicitacao);
	}

	public void setStatusSolicitacao(StatusSolicitacao status) {
		this.statusSolicitacao = status.getCod();
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Olá prestador(a)! Nós do AppServices temos uma nova solicitação de serviço para você."+"\n");
		builder.append( "Entre em seu aplicativo e seja o primeiro a enviar um orçamento para "+getCliente().getNome()+"!"+"\n");
		builder.append("\n");
		builder.append("----------- **   Solicitação de Serviço   ** -------------");
		builder.append("\n");
		builder.append("Cliente: ");
		builder.append(getCliente().getNome() +" "+ getCliente().getSobrenome() + "\n");
		builder.append("Produto / Serviço: ");
		builder.append(getProdutoServico()+"\n");
		builder.append("\n");
		builder.append("----------- ** Informações do Serviço ** -------------");
		builder.append("\n"); 
		for(ItensSolicitacao is : getItemServico()) {
			builder.append(i +" ) "+LayoutItensToString(i++)+"\n");
			builder.append(" - "+is.toString()+"\n");
		}	
		return builder.toString();
	}

	
	private String LayoutItensToString(int i) {
		// TODO Auto-generated method stub
		String question="#";
		
		switch (i) {
		case 0:
			question =  "Detalhes do produto ou serviço: ";
			break;
		case 1:
			question = "Defeitos ou circunstâncias: "; 
			break;
		case 2:
			question = "Desejo do cliente: "; 
			break;
		}
		
		return question;
	}
	
	
}
