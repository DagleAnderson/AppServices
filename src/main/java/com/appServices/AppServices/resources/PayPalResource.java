package com.appServices.AppServices.resources;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appServices.AppServices.Services.PayPalClientService;


@RestController
@RequestMapping(value="/paypal")
public class PayPalResource{
	 private final PayPalClientService payPalClient;
	 
	    @Autowired
	    PayPalResource(PayPalClientService payPalClient){
	        this.payPalClient = payPalClient;
	    }

	    @CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping(value = "/make/payment")
	    public Map<String, Object> makePayment(@RequestParam("sum") String sum){
	        return payPalClient.createPayment(sum);
	    }

	    @CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping(value = "/complete/payment")
	    public Map<String, Object> completePayment(HttpServletRequest request, @RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId){
	        return payPalClient.completePayment(request);
	    }

}
