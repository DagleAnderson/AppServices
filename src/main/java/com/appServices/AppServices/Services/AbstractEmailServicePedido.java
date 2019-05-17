package com.appServices.AppServices.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.appServices.AppServices.domain.Pedido;
import com.appServices.AppServices.domain.enums.StatusAtendimento;


public abstract class AbstractEmailServicePedido implements EmailServicePedido{
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	List<String> emails = new ArrayList<>();
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj){
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		// TODO Auto-generated method stub
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Parabéns!!!Um novo Orçamento foi aprovado. Nº do Pedido:#"+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		
		return sm;
	}
	
	
	protected String htmlFromTemplatePedido(Pedido obj,StatusAtendimento atendimento) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		
		if(atendimento.getCod() == 1) {
			return templateEngine.process("email/pedido/sendNewPedido", context);
			}
			if(atendimento.getCod() == 2) {
				return templateEngine.process("email/pedido/sendPedidoAtendido", context);	
			}
			if(atendimento.getCod() == 3) {
				return templateEngine.process("email/pedido/sendPedidoNaoAtendido", context);	
			}
		
			return null;
	
	}
	
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj,StatusAtendimento atendimento) { 
		try {
		MimeMessage mm = prepareMimeMessageFromPedido(obj,atendimento);
		sendHtmlEmail(mm);
		}
		catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
}

	protected  MimeMessage prepareMimeMessageFromPedido(Pedido obj,StatusAtendimento atendimento) throws MessagingException {
		MimeMessage mimeMessage =  javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true );
		
		this.GetEmails(obj);
		mmh.setTo(emails.toArray(new String[emails.size()]));
		mmh.setFrom(sender);
		
		//verificação: 'QUAL O CABEÇALHO?'
				if(atendimento.getCod() == 1) {
					mmh.setSubject("Parabéns!Pedido #"+ obj.getId()+" foi confirmado e os dados já foram liberados para o atendimento.");
				}
				if(atendimento.getCod() == 2){
					mmh.setSubject(" Atendimento realizado com sucesso!Pedido #"+ obj.getId());
				}
				if(atendimento.getCod() == 3){
					mmh.setSubject(" Que pena!Lamentamos essa situação.Pedido #"+ obj.getId()+" Não foi atendido.");
				}
				
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj,atendimento),true);
		
		return mimeMessage;
	}
	
private void GetEmails(Pedido obj) {
	
	this.emails.add(obj.getCliente().getEmail());	
	this.emails.add(obj.getPrestador().getEmail());	
		
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
