package com.appServices.AppServices.Services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.SolicitacaoServico;

public interface EmailServiceSolicitacao {

	void sendOrderConfirmationEmail(SolicitacaoServico obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(SolicitacaoServico obj); 
	
	void sendHtmlEmail(MimeMessage msg); 
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
	 
}
