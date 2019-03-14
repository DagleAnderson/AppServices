package com.appServices.AppServices.Services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Orcamento;

public abstract class AbstractEmailServiceOrcamento implements EmailServiceOrcamento{
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Orcamento obj){
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrcamento(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrcamento(Orcamento obj) {
		// TODO Auto-generated method stub
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject(" Nova Solicitação de Serviço para você! Código:#"+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		
		return sm;
	}
	
	
	protected String htmlFromTemplateOrcamento(Orcamento obj) {
		Context context = new Context();
		context.setVariable("orcamento", obj);
		
		return templateEngine.process("email/orcamento", context);
	
	}
	
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Orcamento obj) { 
		try {
		MimeMessage mm = prepareMimeMessageFromOrcamento(obj);
		sendHtmlEmail(mm);
		}
		catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected  MimeMessage prepareMimeMessageFromOrcamento(Orcamento obj) throws MessagingException {
		MimeMessage mimeMessage =  javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true );
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido Confirmado! Código:"+ obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateOrcamento(obj),true);
		
		return mimeMessage;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente,String newPass){
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente,newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: "+ newPass);
		
		return sm;
	}
		
}
