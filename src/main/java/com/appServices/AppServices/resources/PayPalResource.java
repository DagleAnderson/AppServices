package com.appServices.AppServices.resources;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appServices.AppServices.config.PayPalClientConfig;

@RestController
@RequestMapping(value="/paypal")
public class PayPalResource{
	private final PayPalClientConfig payPalClient;
	
	    @Autowired
	    PayPalResource(PayPalClientConfig payPalClient){
	        this.payPalClient = payPalClient;
	    }

	    @PostMapping(value = "/make/payment")
	    public Map<String, Object> makePayment(@RequestParam("soma") String soma){
	        return payPalClient.createPayment(soma);
	    }
	    
	    @PostMapping(value = "/complete/payment")
	    public Map<String, Object> completePayment(HttpServletRequest request){
	        return payPalClient.completePayment(request);
	    }
	
}
