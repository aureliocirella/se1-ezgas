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
import org.junit.jupiter.api.BeforeAll;
import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;

public class TestController {

	public static UserDto userDto;
	public static GasStationDto gasStationDto;
	
	@BeforeAll
	public void init() throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("http://localhost:8080/user/saveUser");
	    
	    String json = "{\"userId\":null,\"userName\":\"Claudia\",\"password\":\"claudio\",\"email\":\"claudio@me.it\",\"reputation\":0,\"admin\":false}";
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	 
	    HttpResponse response = client.execute(httpPost);
	    
	    String jsonFromResponse = EntityUtils.toString(response.getEntity()); 
	    
	    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
	    
	    userDto = mapper.readValue(jsonFromResponse, UserDto.class);
	   
	    
	    client.close();
	    
	    
		
	}
	
	@Test
	public void testAllUser() throws ClientProtocolException, IOException{
		
		HttpUriRequest httpGet = new HttpGet("http://localhost:8080/user/getAllUsers");
		
		HttpResponse response = HttpClientBuilder.create().build().execute(httpGet); 
		 
		assert(response.getStatusLine().getStatusCode() == 200);
		
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
	public void testUserById() throws ClientProtocolException, IOException{
		
		HttpUriRequest httpGet = new HttpGet("http://localhost:8080/user/getUser/"+userDto.getUserId());
		
		HttpResponse response = HttpClientBuilder.create().build().execute(httpGet); 
		 
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		assert(response.getStatusLine().getStatusCode() == 200);
		assert(jsonFromResponse.contains(userDto.getUserId().toString()));
		
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
		
		
		HttpUriRequest httpGet = new HttpGet("http://localhost:8080/gasstation/getGasStationsWithCoordinates/41.4632681/15.5227678/superplus/Enjoy");
		
		HttpResponse response = HttpClientBuilder.create().build().execute(httpGet); 
	    
		String jsonFromResponse = EntityUtils.toString(response.getEntity()); 
	    
	    
		assert(response.getStatusLine().getStatusCode() == 200);
	    assert(jsonFromResponse.contains("Federico"));
		
		
	}
	
	@Test
	public void testincreaseUserReputation() throws ClientProtocolException, IOException{
		 try	
		 {
	
			HttpUriRequest httpGet = new HttpGet("http://localhost:8080/user/getUser/"+userDto.getUserId());
			HttpResponse response = HttpClientBuilder.create().build().execute(httpGet); 
			String jsonFromResponse = EntityUtils.toString(response.getEntity()); 
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
		    Integer reputationBefore = mapper.readValue(jsonFromResponse, UserDto.class).getReputation();
				 
			HttpUriRequest httpResuest = new HttpPost("http://localhost:8080/user/increaseUserReputation/"+userDto.getUserId());
			response = HttpClientBuilder.create().build().execute(httpResuest);
			jsonFromResponse = EntityUtils.toString(response.getEntity()); 	 
			
			Integer reputationAfter = Integer.valueOf(jsonFromResponse);
			
			reputationBefore= ( reputationBefore>=5)?5:reputationBefore+1;
			
			assertEquals(reputationBefore,reputationAfter);
//		jsonFromResponse=  String.valueOf(Integer.valueOf(jsonFromResponse)+1);
//		
//		// System.out.print(String.format("%1$s == %2$s ",jsonFromResponse,jsonFromResponse2 ) );
//		assertEquals(jsonFromResponse ,jsonFromResponse2);
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
			HttpUriRequest httpGet = new HttpGet("http://localhost:8080/user/getUser/"+userDto.getUserId());
			HttpResponse response = HttpClientBuilder.create().build().execute(httpGet); 
			String jsonFromResponse = EntityUtils.toString(response.getEntity()); 
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
		    Integer reputationBefore = mapper.readValue(jsonFromResponse, UserDto.class).getReputation();					 
			HttpUriRequest httpResuest = new HttpPost("http://localhost:8080/user/decreaseUserReputation/"+userDto.getUserId());
			response = HttpClientBuilder.create().build().execute(httpResuest);
			jsonFromResponse = EntityUtils.toString(response.getEntity()); 	 
			Integer reputationAfter = Integer.valueOf(jsonFromResponse);
				
			reputationBefore= ( reputationBefore<=-5)?-5:reputationBefore-1;
				
			assertEquals(reputationBefore,reputationAfter);
			
//			if(jsonFromResponse!="0")
//			{
//			  Integer reputation = Integer.valueOf(jsonFromResponse); 
//			  if(reputation--<-5)
//			  {				
//				  reputation = -5; 
//			  }
//			  	jsonFromResponse=  String.valueOf(Integer.valueOf(reputation));
//			}
//		   else
//			   jsonFromResponse= "0";  
//		   assertEquals(jsonFromResponse ,jsonFromResponse2);
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
		 
	    String json = "{\"user\":\""+userDto.getEmail()+"\",\"pw\":\""+userDto.getPassword()+"\"}";
	    StringEntity entity = new StringEntity(json);
	    httpResuest.setEntity(entity);
	    httpResuest.setHeader("Accept", "application/json");
	    httpResuest.setHeader("Content-type", "application/json");
		HttpResponse response = HttpClientBuilder.create().build().execute(httpResuest);
		String jsonFromResponse = EntityUtils.toString(response.getEntity()); 
		assert(jsonFromResponse.contains(userDto.getEmail()));
		assert(response.getStatusLine().getStatusCode() == 200);
//   	    response = HttpClientBuilder.create().build().execute(httpResuest);
//			  
//		 System.out.print(jsonFromResponse );
//			//  assertEquals(jsonFromResponse ,jsonFromResponse2);
		 }
		 catch (Exception e)
		 {
			fail("Login failed!");
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
	    
	    String jsonFromResponse = EntityUtils.toString(response.getEntity()); 
	    
	    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
	    
	    gasStationDto = mapper.readValue(jsonFromResponse, GasStationDto.class); 
	     
	    assert(response.getStatusLine().getStatusCode() == 200);
	    assert(userDto.getEmail().equals("claudio@me.it"));
	    client.close();
 
	    assert(response.getStatusLine().getStatusCode() == 200);
	    assert(gasStationDto.getLat()==41.4632681 && gasStationDto.getLon()==15.5227678);
	    client.close();
	}
	
	@Test
	public void testgetGasStation() throws ClientProtocolException, IOException{
		 try	
		 { 
   	     CloseableHttpClient client = HttpClients.createDefault(); 
   	     HttpPost httpResuest = new HttpPost("http://localhost:8080/gasstation/getGasStation/87"); 
		 HttpResponse response = client.execute(httpResuest);
		 String jsonFromResponse = EntityUtils.toString(response.getEntity());
		 assert(jsonFromResponse.contains("Federico"));
//		 ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
//		    
//         GasStationDto  gasStationDto = mapper.readValue(jsonFromResponse, GasStationDto.class);    
		 
         assert(response.getStatusLine().getStatusCode() == 200);
         client.close();
		 }
		 catch (Exception e)
		 {
			fail("getGasStation failed!");
		 }
		
	}
	
	
	
	
	@Test
	public void testAllGasStations() throws ClientProtocolException, IOException{
		
		HttpUriRequest httpGet = new HttpGet("http://localhost:8080/gasstation/getAllGasStations");
		
		HttpResponse response = HttpClientBuilder.create().build().execute(httpGet); 
		 
		assert(response.getStatusLine().getStatusCode() == 200);
		
	}
		
	@Test
	@AfterAll
	public void testDeleteGasStation() 
	  throws ClientProtocolException, IOException {
		 
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpDelete httpDelete = new HttpDelete("http://localhost:8080/user/deleteUser/"+gasStationDto.getGasStationId());
		HttpResponse response = client.execute(httpDelete);
		assert(response.getStatusLine().getStatusCode() == 200);
	    client.close();
	}
	
}
