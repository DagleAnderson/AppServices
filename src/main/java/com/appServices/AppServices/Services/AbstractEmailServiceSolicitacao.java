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
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.SolicitacaoServico;

public abstract class AbstractEmailServiceSolicitacao implements EmailServiceSolicitacao{
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	List<String> emails = new ArrayList<>();
		
	@Override
	public void sendOrderConfirmationEmail(SolicitacaoServico obj){
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromSolicitacao(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromSolicitacao(SolicitacaoServico obj) {
		// TODO Auto-generated method stub
		SimpleMailMessage sm = new SimpleMailMessage();
		GetEmailsPrestadores(obj);
		sm.setTo(emails.toArray(new String[emails.size()]));
		sm.setFrom(sender);
		sm.setSubject(" Nova Solicitação de Serviço para você! Código:#"+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());		
			
		return sm;
	}
	
	
	private void GetEmailsPrestadores(SolicitacaoServico obj) {
		
		System.out.println("profissao Id:"+obj.getProfissao().getId());
		System.out.println("numero de prestadores:"+obj.getProfissao().getPrestador().size());
		
		for( int n=0; n < obj.getProfissao().getPrestador().size();n++) {
			Prestador prestEmail = new Prestador();
				prestEmail = obj.getProfissao().getPrestador().get(n);
				String emailsPrestadores = prestEmail.getEmail();
			
				this.emails.add(emailsPrestadores);		
		}
		
		System.out.println(this.emails);
		
		
	}

	protected String htmlFromTemplateSolicitacao(SolicitacaoServico obj) {
		Context context = new Context();
		context.setVariable("solicitacao", obj);
		
		return templateEngine.process("email/solicitacao/sendNewSolicitacao", context);
	
	}
	
	
	@Override
	public void sendOrderConfirmationHtmlEmail(SolicitacaoServico obj) { 
		try {
		MimeMessage mm = prepareMimeMessageFromSolicitacaoServico(obj);
			sendHtmlEmail(mm);
		}
		catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected  MimeMessage prepareMimeMessageFromSolicitacaoServico(SolicitacaoServico obj) throws MessagingException {
		MimeMessage mimeMessage =  javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true );
		GetEmailsPrestadores(obj);
		mmh.setTo(emails.toArray(new String[emails.size()]));
		mmh.setFrom(sender);
		mmh.setSubject("Nova Solicitação de Serviço para você! Código:#"+ obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateSolicitacao(obj),true);
		
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
