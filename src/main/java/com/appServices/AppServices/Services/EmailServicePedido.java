package com.appServices.AppServices.Services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Pedido;
import com.appServices.AppServices.domain.enums.StatusAtendimento;

public interface EmailServicePedido {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj,StatusAtendimento atendimento); 
	
	void sendHtmlEmail(MimeMessage msg); 
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
	 
}
