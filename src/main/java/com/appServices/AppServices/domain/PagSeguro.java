package com.appServices.AppServices.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.appServices.AppServices.domain.enums.StatusPagamento;

@Entity
public class PagSeguro implements  Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	private double valor;
	private Integer statusPagamento;
	private String URLCreatePag;
	private String nCode;
	private String nType;
	
	public PagSeguro(){
		
	}
	
	
	public PagSeguro(Integer id, Cliente cliente, double valor, StatusPagamento statusPagamento, String uRLCreatePag,String nCode,String nType) {
		this.id = id;
		this.cliente = cliente;
		this.valor = valor;
		this.statusPagamento = statusPagamento.getCod();
		this.URLCreatePag = uRLCreatePag;
		this.nCode = nCode;
		this.nType = nType;
		
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
		PagSeguro other = (PagSeguro) obj;
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



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public double getValor() {
		return valor;
	}



	public void setValor(double valor) {
		this.valor = valor;
	}



	public String getURLCreatePag() {
		return URLCreatePag;
	}


	public void setURLCreatePag(String uRLCreatePag) {
		URLCreatePag = uRLCreatePag;
	}


	public StatusPagamento getStatusPagamento() {
		return StatusPagamento.toEnum(this.statusPagamento);
	}


	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento.getCod();
	}


	public String getnCode() {
		return nCode;
	}


	public void setnCode(String nCode) {
		this.nCode = nCode;
	}


	public String getnType() {
		return nType;
	}


	public void setnType(String nType) {
		this.nType = nType;
	}
	
	
	
}
