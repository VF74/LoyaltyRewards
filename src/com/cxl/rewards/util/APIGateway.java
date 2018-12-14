package com.cxl.rewards.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 
 * @author rmoon
 *
 */
public class APIGateway 
{
	public static void main(String[] args)
	{
		APIGateway api = new APIGateway();
		StringBuilder output = api.getRequest();
		System.out.println( output.toString() );
	}
	
	/**
	 * Return the JSON from the API Gateway GET request.
	 * @return
	 */
	public StringBuilder getRequest() 
	{
		StringBuilder jsonRespone = new StringBuilder();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		//NOTE: The URL is hard coded since this is a HackAThon, otherwise this would be read from DB or property file.
		HttpGet getRequest  = new HttpGet("https://48qtjhl8m9.execute-api.us-east-1.amazonaws.com/QA");
		getRequest.addHeader("accept", "application/json");
		try {
			
			CloseableHttpResponse apiResponse = httpclient.execute(getRequest );
		    		    
		    if(apiResponse.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + apiResponse.getStatusLine().getStatusCode());
			}
		    
			BufferedReader br = new BufferedReader(new InputStreamReader((apiResponse.getEntity().getContent())));		    
		    
			String output="";
			while ((output = br.readLine()) != null) {
				jsonRespone.append(output);
			}

			httpclient.getConnectionManager().shutdown();			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonRespone;
	}

}
