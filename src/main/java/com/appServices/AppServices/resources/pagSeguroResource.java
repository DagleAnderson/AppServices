package com.appServices.AppServices.resources;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appServices.AppServices.Services.ClienteService;
import com.appServices.AppServices.Services.PagSeguroService;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.EnderecoCliente;
import com.appServices.AppServices.domain.PagSeguro;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.enums.StatusPagamento;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.domain.Credentials;
import br.com.uol.pagseguro.domain.Item;
import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.domain.Sender;
import br.com.uol.pagseguro.domain.Shipping;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.service.NotificationService;


@RestController
@RequestMapping(value="/pagamento")
public class pagSeguroResource {
	
	private String pagamento;
	private String url_redirect;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	PagSeguroService pagSeguroService;
	
	@Value("${pagSeguro.email}")
	private String email;
	
	@Value("${pagSeguro.token}")
	private String token;
	
	
	@RequestMapping(value="/pagSeguro/{id}",method = RequestMethod.GET)
	public ResponseEntity<PagSeguro> find(@PathVariable Integer id){
		
		PagSeguro objOp = pagSeguroService.findById(id);
		
		return ResponseEntity.ok().body(objOp);
	}
	
	
	
	@RequestMapping(value="/pagseguro-createpayment",method = RequestMethod.POST)
	public ResponseEntity<PagSeguro> CriarPagamento( @RequestParam (value="cliente") Integer cliente){
		try {
			Cliente cli = clienteService.find(cliente);
			
			//ResponseEntity<String> response = new ResponseEntity<String>(null);
			
			PaymentRequest request = new PaymentRequest();
			request.setReference("LBD01");
			request.setCurrency(Currency.BRL);
			request.setSender(getSender(cli));
			request.setShipping(getShipping(cli));
			request.addItem(getItem());
			request.setNotificationURL("https://localhost:8080/pagamento/pagseguro-notificacao");
			request.setRedirectURL("https://localhost:8080/categorias");
			
			this.url_redirect = request.register(getCredentials());
			System.out.println(url_redirect);
			this.url_redirect = request.register(getCredentials()).substring(59);
			PagSeguro pagSeg = new PagSeguro(null, cli, 1.99,StatusPagamento.PENDENTE,url_redirect,null,null);
			
			pagSeguroService.insert(pagSeg);	
			
			return   ResponseEntity.ok().body(pagSeg);
			
		} catch (PagSeguroServiceException e) {
			Logger.getLogger(e.getMessage());
		}
		
		return null;
	}

	
	//Dados do cliente solicitante do serviço
	private Sender getSender(Cliente cliente) {
		
		Sender sender  = new Sender();
		sender.setName(cliente.getNome()+' '+cliente.getSobrenome());
		sender.setEmail(cliente.getEmail());
		
		return sender;
	}
	
	//Frete
	private Shipping getShipping(Cliente cliente) {
		Shipping shipping = new Shipping();
		shipping.setAddress(getAddress(cliente.getEndereco()));
		shipping.setCost(new BigDecimal("0.00"));
		shipping.setType(ShippingType.NOT_SPECIFIED);
		
		return shipping;
	}
	
	//Enderecpo
	private Address getAddress(EnderecoCliente end) {
		Address address = new Address();
		address.setCity((end.getCidade()));
		address.setComplement(end.getComplemento());
		address.setCountry("Brasil");
		address.setPostalCode(end.getCep());
		address.setNumber(Integer.toString(end.getNumero()));
		address.setDistrict(end.getBairro());
		
		return address;
	}



	private Item getItem(){
		Item item = new Item();
		item.setId("001");
		item.setDescription("Dados do prestador");
		item.setQuantity(1);
		item.setAmount(new BigDecimal("0.25"));
		//item.setShippingCost(new BigDecimal("1.99"));
		return item;
	}
	
	private Credentials getCredentials() throws PagSeguroServiceException {
		// TODO Auto-generated method stub
		return new AccountCredentials(email, token);
	}

	
	@RequestMapping(value="/pagseguro-notificacao",method = RequestMethod.POST)
	public @ResponseBody String RegistrarNotificacao(
			@RequestParam(value="notificationCode") String nCode,
			@RequestParam(value="notificationType") String nType){
		
		try {
			
			Transaction transaction = NotificationService.checkTransaction(getCredentials(),nCode);
			
			switch (transaction.getStatus()) {
				case PAID:
					  pagamento ="pago";
					  System.out.println(pagamento);	
					
					break;
					
				case CANCELLED:
					 pagamento ="cancelado";
					  System.out.println(pagamento);	
					break;
					
				case WAITING_PAYMENT:
					 pagamento ="aguardando";
					  System.out.println(pagamento);			
					break;
				
				case IN_ANALYSIS:	
					 pagamento ="análise";
					  System.out.println(pagamento);	
					break;	

			default:
				break;
			}
			
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
		}
				
		
		return   pagamento;
		
	}

}
	

