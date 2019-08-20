package com.appServices.AppServices.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.appServices.AppServices.Services.DBService;
import com.appServices.AppServices.Services.EmailServiceOrcamento;
import com.appServices.AppServices.Services.EmailServicePedido;
import com.appServices.AppServices.Services.EmailServiceSolicitacao;
import com.appServices.AppServices.Services.SmtpEmailServiceOrcamento;
import com.appServices.AppServices.Services.SmtpEmailServicePedido;
import com.appServices.AppServices.Services.SmtpEmailServiceSolicitacao;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Autowired
	private DBService dbService;
	
	/* {AppServices/src/main/resources/application-prod.properties} */
	@Value("${spring.jpa.hibernate.ddl-auto}")
	
	private String strategy;
	
	@Bean
	public Boolean isntantiateDataBase() throws ParseException{
		
		if(!"create".equals(strategy)) {
			return false;
		}
			dbService.instantiateTestDataBase();	
		return true;
	}
	
	@Bean
	public EmailServicePedido emailServicePedido() {
		return new SmtpEmailServicePedido();
	}
	
	@Bean
	public EmailServiceSolicitacao emailServiceSolicitacao() {
		return new SmtpEmailServiceSolicitacao();
	}
	
	@Bean
	public EmailServiceOrcamento emailServiceOrcamento() {
		return new SmtpEmailServiceOrcamento();
	}
}
