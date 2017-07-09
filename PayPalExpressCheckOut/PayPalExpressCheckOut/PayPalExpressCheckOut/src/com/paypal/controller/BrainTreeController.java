package com.paypal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.paypal.checkoutexpress.CheckOutExpress;

@Path("/braintreecontroller")

public class BrainTreeController {

	@Context
	private HttpServletRequest request;

	@GET
	@Path("/clienttoken")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientToken(@Context HttpHeaders headers) {
		
		JSONObject jsonResponse = new JSONObject();
		String responseValue = new CheckOutExpress().generateClientToken();
		String response = "{\"token\":" +"\""+ responseValue.trim() +"\" }" ;
		
		System.out.println("Response Token:" +response);
		
		if(!response.isEmpty()){
			JSONObject respObj = new JSONObject(response);
			jsonResponse.put("ResponseStatus", "Success");
			jsonResponse.put("ResponseMessage", respObj);
			jsonResponse.put("STATUS", "200");
			return Response.status(200).entity(response).build();
		}
		else {
			jsonResponse.put("ResponseStatus", "Failure");
			//jsonResponse.put("ResponseMessage", respObj);
			jsonResponse.put("STATUS", "500");
			return Response.status(500).entity(response).build();
		}
		
		/*return Response.ok().entity(jsonResponse.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, OPTIONS")
				.header("Access-Control-Request-Headers", "Authorization").header("Access-Control-Max-Age", "1")
				.build();*/
	}

	
	@POST
	@Path("/transaction")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTransaction(@Context HttpHeaders headers, String payload) {
		//System.out.println("You are in createTransaction");
		JSONObject jsonResponse = new JSONObject(payload);
		String response = new CheckOutExpress().createTransactionRequest(jsonResponse);
		if(!response.isEmpty()){
			JSONObject respObj = new JSONObject(response);
			jsonResponse.put("ResponseStatus", "Success");
			jsonResponse.put("ResponseMessage", respObj);
			jsonResponse.put("STATUS", "200");
			return Response.status(200).entity(response).build();
		}
		else {
			jsonResponse.put("ResponseStatus", "Failure");
			//jsonResponse.put("ResponseMessage", respObj);
			jsonResponse.put("STATUS", "500");
			return Response.status(500).entity(response).build();
		}
		
		/*return Response.ok().entity(jsonResponse.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, OPTIONS")
				.header("Access-Control-Request-Headers", "Authorization").header("Access-Control-Max-Age", "1")
				.build();*/
		}
	
	@POST
	@Path("/checkout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response handle(@Context HttpServletRequest request, @QueryParam("payment_method_nonce") String nonce){
		
		System.out.println("EndPoint:" +nonce);
		new CheckOutExpress().transactionRequest(nonce);
		String response1 = "Success";
		return Response.status(200).entity(response1).build();
		
		
	}
		
}


