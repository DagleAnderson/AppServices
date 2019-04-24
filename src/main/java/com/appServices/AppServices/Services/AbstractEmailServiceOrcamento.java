package com.appServices.AppServices.Services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.enums.TipoSituacao;

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
		sm.setSubject(" Você tem um novo Orçamento! Código:#"+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		
		return sm;
	}
	
	
	protected String htmlFromTemplateOrcamento(Orcamento obj,TipoSituacao situacao) {
		Context context = new Context();
		context.setVariable("orcamento", obj);
		
		if(situacao.getCod() == 1) {
		return templateEngine.process("email/orcamento/sendNewOrcamento", context);
		}
		if(situacao.getCod() == 3) {
			return templateEngine.process("email/orcamento/sendOrcamentoAprovado", context);	
		}
		if(situacao.getCod() == 4) {
			return templateEngine.process("email/orcamento/sendOrcamentoRejeitado", context);	
		}
	
		return null;
	}
	
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Orcamento obj,TipoSituacao situacao) { 
		try {
			//System.out.println(situacao.getCod());
			//if(situacao.getCod()!=2){
			MimeMessage mm = prepareMimeMessageFromOrcamento(obj, situacao);
			sendHtmlEmail(mm);
			//}
		}
		catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected  MimeMessage prepareMimeMessageFromOrcamento(Orcamento obj,TipoSituacao situacao) throws MessagingException {
		MimeMessage mimeMessage =  javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true );
		
		//verificação: 'PARA QUEM O EMAIL VAI?'
		if(situacao.getCod() == 1) {
			mmh.setTo(obj.getCliente().getEmail());
			System.out.println("foi para o cliente!");
		}else {
			mmh.setTo(obj.getPrestador().getEmail());	
			System.out.println("foi para o prestador!");
		}	
			
		
		mmh.setFrom(sender);
		
		//verificação: 'QUAL O CABEÇALHO?'
		if(situacao.getCod() == 1) {
			mmh.setSubject(" Você tem um novo Orçamento! Código:#"+ obj.getId());
		}
		if(situacao.getCod() == 3){
			mmh.setSubject(" Parabéns! Seu Orçamento foi aprovado. Código:#"+ obj.getId());
		}
		if(situacao.getCod() == 4){
			mmh.setSubject(" Que pena! Seu Orçamento foi rejeitado. Código:#"+ obj.getId());
		}
		
			
		
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateOrcamento(obj,situacao),true);
		
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
