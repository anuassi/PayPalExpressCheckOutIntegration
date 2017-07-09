package com.paypal.checkoutexpress;

import java.math.BigDecimal;
import java.util.List;

import org.json.JSONObject;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionOptionsRequest;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.ValidationErrors;

public class CheckOutExpress {

	private BraintreeGateway gateway;
	//private String paymentnonce = "";
	public CheckOutExpress(){
	
		String accessToken = "access_token$sandbox$2kw6dtz5cpp94kc7$e0aee25a87e1fedc1dd6d3d5b501d6c2";
		this.gateway = new BraintreeGateway(accessToken);
		if(gateway != null)
			System.out.println("Gateway Object:" +gateway.toString());
		else
		 System.out.println("Error Gateway Object could not be generated!!");
	}
	
	public String generateClientToken(){
		
		if ( gateway != null)
			return this.gateway.clientToken().generate();
		else
			return "";
		
	}

	public String createTransactionRequest(JSONObject transReq){
		
		
		System.out.print("nonce:" +transReq.getString("paymentnonce") +"\n");
		System.out.print("orderid:" +transReq.getString("orderid") +"\n");
		System.out.print("orderDesc:" +transReq.getString("orderdescription") +"\n");
		System.out.print("CountryCode:" +transReq.getString("countrycode") +"\n");
		System.out.print("FirstName:" +transReq.getString("firstname") +"\n");
		System.out.print("LastName:" +transReq.getString("lastname") +"\n");
		System.out.print("Companyname:" +transReq.getString("companyname") +"\n");
		System.out.print("line1address:" +transReq.getString("line1address") +"\n");
		System.out.print("line2address:" +transReq.getString("line2address") +"\n");
		System.out.print("City:" +transReq.getString("city") +"\n");
		System.out.print("State:" +transReq.getString("state") +"\n");
		System.out.print("Code:" +transReq.getString("postalcode") +"\n");
		
		System.out.println("Starting to create request object!!");
		TransactionRequest request = new TransactionRequest();
		
				BigDecimal amt = new BigDecimal(10.00);
				request.amount(amt); //transactionamount
			    request.merchantAccountId("USD"); //currency
			    request.paymentMethodNonce(transReq.getString("paymentnonce"));
			    request.orderId(transReq.getString("orderid"));
			    request.descriptor().
			      name(transReq.getString("orderdescription")).
			      done();
			    
			    request.shippingAddress()
			        .firstName(transReq.getString("firstname"))
			        .lastName(transReq.getString("lastname"))
			        .company(transReq.getString("companyname"))
			        .streetAddress(transReq.getString("line1address"))
			        .extendedAddress(transReq.getString("line2address"))
			        .locality(transReq.getString("city"))
			        .region(transReq.getString("state"))
			        .postalCode(transReq.getString("postalcode"))
			        .countryCodeAlpha2("IN")
			        .done();
			   request.options().
			      paypal().
			        customField("PayPal custom field").
			        description("Description for PayPal email receipt").
			        done();
			   request.options().storeInVaultOnSuccess(true).done();
			   request.options().submitForSettlement(true);
			   
			   
			//System.out.println("Transaction Request is created!!");      
			Result<Transaction> saleResult = gateway.transaction().sale(request);
			if (saleResult.isSuccess()) {
				  Transaction transaction = saleResult.getTarget();
				  System.out.println("Success ID: " + transaction.getId());
				  return transaction.getId();
				} else {
				
				  System.out.println("Message: " + saleResult.getMessage());
				  return "";
				}
	}
	
	public void transactionRequest(String paymentnonce){
		System.out.println("Nonce:" +paymentnonce);
	}
		
	public static void main(String[] args){
		
		CheckOutExpress chk = new CheckOutExpress();
		String clientToken = chk.generateClientToken();
		if (clientToken.isEmpty())
			System.out.println("Error in generating Client Token");
		else
			System.out.println("Client Token:" +clientToken);
		
	}
}


