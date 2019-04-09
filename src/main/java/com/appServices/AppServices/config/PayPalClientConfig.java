package com.appServices.AppServices.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;


@Configuration
public class PayPalClientConfig {

	//@Value("${payPal.clientId}")
	String clientId="AbfznHXboGdtc_VGn9zD5k3NI4aWfCgAub69Ja5cQCbWg_Fxe0pE8a8KBaxK2tlTuP3yBV1jxhKS_1CC";
	
	//@Value("${payPal.clientSecret}")
	String clientSecret="ENk4wX6agJ-qmNYb5PFXIchwQaBF8djV5rPoXaWLRMUj4Yu-WV88BHElWtaEND-Ia1KruLYBQhbJnnJH";
	
	
	public Map<String, Object> createPayment(String soma){
	    Map<String, Object> response = new HashMap<String, Object>();
	    Amount montante = new Amount();
	    montante.setCurrency("USD");
	    montante.setTotal(soma);
	    Transaction transacao = new Transaction();
	    transacao.setAmount(montante);
	    List<Transaction> transacoes = new ArrayList<Transaction>();
	    transacoes.add(transacao);

	    Payer pagador = new Payer();
	    pagador.setPaymentMethod("paypal");

	    Payment pagamento = new Payment();
	    pagamento.setIntent("sale");
	    pagamento.setPayer(pagador);
	    pagamento.setTransactions(transacoes);

	    RedirectUrls redirectUrls = new RedirectUrls();
	    redirectUrls.setCancelUrl("http://localhost:4200/cancel");
	    redirectUrls.setReturnUrl("http://localhost:4200/");
	    pagamento.setRedirectUrls(redirectUrls);
	    Payment createdPagamento;
	    try {
	        String redirectUrl = "";
	        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
	        createdPagamento = pagamento.create(context);
	        if(createdPagamento!=null){
	            List<Links> links = createdPagamento.getLinks();
	            for (Links link:links) {
	                if(link.getRel().equals("approval_url")){
	                    redirectUrl = link.getHref();
	                    break;
	                }
	            }
	            response.put("status", "success");
	            response.put("redirect_url", redirectUrl);
	        }
	    } catch (PayPalRESTException e) {
	        System.out.println("Ocorreu um erro durante a criação do pagamento!");
	    }
	    return response;
	}
	
	
	public Map<String, Object> completePayment(HttpServletRequest req){
	    Map<String, Object> response = new HashMap();
	    Payment payment = new Payment();
	    payment.setId(req.getParameter("pagamentoId"));

	    PaymentExecution paymentExecution = new PaymentExecution();
	    paymentExecution.setPayerId(req.getParameter("PagadorID"));
	    try {
	        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
	        Payment createdPayment = payment.execute(context, paymentExecution);
	        if(createdPayment!=null){
	            response.put("status", "success");
	            response.put("payment", createdPayment);
	        }
	    } catch (PayPalRESTException e) {
	        System.err.println(e.getDetails());
	    }
	    return response;
	}
}
