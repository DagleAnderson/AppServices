package com.appServices.AppServices.Services;

import org.springframework.mail.SimpleMailMessage;

import com.appServices.AppServices.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	
}
