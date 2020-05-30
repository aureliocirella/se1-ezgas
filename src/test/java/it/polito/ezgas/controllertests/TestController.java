package it.polito.ezgas.controllertests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;

public class TestController {

	public static UserDto userDto;
	@Test
	public void testAllUser() throws ClientProtocolException, IOException{
		
		HttpUriRequest httpGet = new HttpGet("http://localhost:8080/user/getAllUsers");
		
		HttpResponse response = HttpClientBuilder.create().build().execute(httpGet); 
		 
		assert(response.getStatusLine().getStatusCode() == 200);
		
	}
	
	@Test
	public void testUserById() throws ClientProtocolException, IOException{
		
		HttpUriRequest httpGet = new HttpGet("http://localhost:8080/user/getUser/284");
		
		HttpResponse response = HttpClientBuilder.create().build().execute(httpGet); 
		 
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		assert(response.getStatusLine().getStatusCode() == 200);
		assert(jsonFromResponse.contains("284"));
		
	}
	
	@Test 
	public void testSaveUser() 
	  throws ClientProtocolException, IOException {
	    CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("http://localhost:8080/user/saveUser");
	    
	    String json = "{\"userId\":null,\"userName\":\"Claudio\",\"password\":\"claudio\",\"email\":\"claudio@me.it\",\"reputation\":0,\"admin\":false}";
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	 
	    HttpResponse response = client.execute(httpPost);
	    
	    String jsonFromResponse = EntityUtils.toString(response.getEntity()); 
	    
	    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
	    
	    userDto = mapper.readValue(jsonFromResponse, UserDto.class); 
 
	    assert(response.getStatusLine().getStatusCode() == 200);
	    assert(userDto.getEmail().equals("claudio@me.it"));
	    client.close();
	}
	
	@Test
	@AfterAll
	public void testDeleteUser() 
	  throws ClientProtocolException, IOException {
		 
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpDelete httpDelete = new HttpDelete("http://localhost:8080/user/deleteUser/"+userDto.getUserId());
		HttpResponse response = client.execute(httpDelete);
		assert(response.getStatusLine().getStatusCode() == 200);
	    client.close();
	}
	
	@Test
	public void testGasStationWithCoordinate() throws ClientProtocolException, IOException{
		
		
		HttpUriRequest httpGet = new HttpGet("http://localhost:8080/gasstation/getGasStationsWithCoordinates/45.060735/7.923549/superplus/Enjoy");
		
		HttpResponse response = HttpClientBuilder.create().build().execute(httpGet); 
	    
		String jsonFromResponse = EntityUtils.toString(response.getEntity()); 
	    
	    
		assert(response.getStatusLine().getStatusCode() == 200);
	    assert(jsonFromResponse.contains("Garibaldi"));
		
		
	}
	
	@Test
	public void testincreaseUserReputation() throws ClientProtocolException, IOException{
		 try	
		 {
		HttpUriRequest httpResuest = new HttpPost("http://localhost:8080/user/increaseUserReputation/993");
		HttpResponse response = HttpClientBuilder.create().build().execute(httpResuest);
		 String jsonFromResponse = EntityUtils.toString(response.getEntity()); 	 
			  response = HttpClientBuilder.create().build().execute(httpResuest);
			  String jsonFromResponse2 = EntityUtils.toString(response.getEntity()); 
			  jsonFromResponse=  String.valueOf(Integer.valueOf(jsonFromResponse)+1);
			 // System.out.print(String.format("%1$s == %2$s ",jsonFromResponse,jsonFromResponse2 ) );
			  assertEquals(jsonFromResponse ,jsonFromResponse2);
		 }
		 catch (Exception e)
		 {
			fail("increase User Reputation failed!");
		 }
		
	}
	
	@Test
	public void testdecreaseUserReputation() throws ClientProtocolException, IOException{
		 try	
		 {
		HttpUriRequest httpResuest = new HttpPost("http://localhost:8080/user/decreaseUserReputation/993");
		HttpResponse response = HttpClientBuilder.create().build().execute(httpResuest);
		 String jsonFromResponse = EntityUtils.toString(response.getEntity()); 	 
			  response = HttpClientBuilder.create().build().execute(httpResuest);
			  String jsonFromResponse2 = EntityUtils.toString(response.getEntity()); 
			  if(jsonFromResponse!="0")
			  jsonFromResponse=  String.valueOf(Integer.valueOf(jsonFromResponse)-1);
			  else
				  jsonFromResponse= "0";  
			  assertEquals(jsonFromResponse ,jsonFromResponse2);
		 }
		 catch (Exception e)
		 {
			fail("increase User Reputation failed!");
		 }
		
	}
	
	@Test
	public void testlogin() throws ClientProtocolException, IOException{
		 try	
		 {
			 HttpPost  httpResuest = new HttpPost("http://localhost:8080/user/login/");
		 
	    String json = "{\"user\":admin@ezgas.it,\"pw\":\"admin\"}";
	    StringEntity entity = new StringEntity(json);
	    httpResuest.setEntity(entity);
	    httpResuest.setHeader("Accept", "application/json");
	    httpResuest.setHeader("Content-type", "application/json");
		HttpResponse response = HttpClientBuilder.create().build().execute(httpResuest);
		 String jsonFromResponse = EntityUtils.toString(response.getEntity()); 	 
			  response = HttpClientBuilder.create().build().execute(httpResuest);
			  
		  System.out.print(jsonFromResponse );
			//  assertEquals(jsonFromResponse ,jsonFromResponse2);
		 }
		 catch (Exception e)
		 {
			fail("increase User Reputation failed!");
		 }
		
	}
	
	@Test
	public void testgetGasStation() throws ClientProtocolException, IOException{
		 try	
		 { 
   	     CloseableHttpClient client = HttpClients.createDefault(); 
   	     HttpPost httpResuest = new HttpPost("http://localhost:8080/gasstation/getGasStation/3"); 
		 HttpResponse response = client.execute(httpResuest);
		  String jsonFromResponse = EntityUtils.toString(response.getEntity());
		  
		 ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
		    
         GasStationDto  gasStationDto = mapper.readValue(jsonFromResponse, GasStationDto.class);    
		 
         assert(response.getStatusLine().getStatusCode() == 200);
         client.close();
		 }
		 catch (Exception e)
		 {
			fail("increase User Reputation failed!");
		 }
		
	}
	
	
	@Test 
	public void testSaveGasStation() 
	  throws ClientProtocolException, IOException {
	    CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("http://localhost:8080/gasstation/saveGasStation");
	    
	    String json = "{\"gasStationId\":null,\"gasStationName\":\"Q8\",\"gasStationAddress\":\"Via Federico Spera Foggia Apulia Italy\",\"hasDiesel\":true,\"hasSuper\":true,\"hasSuperPlus\":true,\"hasGas\":false,\"hasMethane\":false,\"carSharing\":\"Enjoy\",\"dieselPrice\":-1,\"superPrice\":-1,\"superPlusPrice\":-1,\"gasPrice\":-1,\"methanePrice\":-1,\"reportUser\":-1,\"reportTimestamp\":null,\"lat\":\"41.4632681\",\"lon\":\"15.5227678\"}";
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	 
	    HttpResponse response = client.execute(httpPost);
	    
	    //String jsonFromResponse = EntityUtils.toString(response.getEntity()); 
	    
	    //ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
	    
	    //gasStationDto = mapper.readValue(jsonFromResponse, GasStationDto.class); 
 
	    assert(response.getStatusLine().getStatusCode() == 200);
	    //assert(gasStationDto.getLat()==41.4632681 && gasStationDto.getLon()==15.5227678);
	    client.close();
	}
	
	@Test
	public void testAllGasStations() throws ClientProtocolException, IOException{
		
		HttpUriRequest httpGet = new HttpGet("http://localhost:8080/gasstation/getAllGasStations");
		
		HttpResponse response = HttpClientBuilder.create().build().execute(httpGet); 
		 
		assert(response.getStatusLine().getStatusCode() == 200);
		
	}
	
	
	
}
