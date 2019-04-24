package com.appServices.AppServices.Services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.enums.TipoSituacao;

public interface EmailServiceOrcamento {

	void sendOrderConfirmationEmail(Orcamento obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Orcamento obj,TipoSituacao situacao); 
	
	void sendHtmlEmail(MimeMessage msg); 
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
	 
}
