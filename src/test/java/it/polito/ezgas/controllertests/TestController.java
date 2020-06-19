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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;

public class TestController {

	public static UserDto userDto;
	public static GasStationDto gasStationDto;
	
	
	   private String testName = "Claudia";
	    private String testEmail = "claudio@me.it";
	    private String testPwd = "claudio";
	    private Integer testReputation = 0;
		
		 
	    private String testGasStationName = "Q8";
	    private String testGasStationAddress = "Via Federico Spera Foggia Apulia Italy";
	    private double testLat = 41.4632681;
	    private double testLon = 15.5227678;
	    private boolean testHasDiesel = true;
	    private double testDiesel = 1.2;
	    private boolean testHasSuper = true;
	    private double testSuper = 1.4;
	    private boolean testHasSuperPlus = true;
	    private double testSuperPlus = 1.45;
	    private boolean testHasGas = true;
	    private double testGas = 0.9;
	    private boolean testHasMethane = true;
	    private double testMethane = 0.9;
	    private String testCarSharing = "enjoy";
	    private Integer radius=1;
	    
//	    private GasStationDto testGasStation2;
//	    private String testGasStationName2 = "testGasStation2";
//	    private String testGasStationAddress2 = "testAddress2";
//	    private double testLat2 = 60.005;
//	    private double testLon2 = 60.005;
//	    private String testCarSharing2 = "testCarSharing2";
	
	@Before
	public void init() throws ClientProtocolException, IOException{
		   gasStationDto = new GasStationDto();
	       gasStationDto.setGasStationName(testGasStationName);
	       gasStationDto.setGasStationAddress(testGasStationAddress);
	       gasStationDto.setLat(testLat);
	       gasStationDto.setLon(testLon);
	       gasStationDto.setHasDiesel(testHasDiesel);
	       gasStationDto.setDieselPrice(testDiesel);
	       gasStationDto.setHasSuper(testHasSuper);
	       gasStationDto.setSuperPrice(testSuper);
	       gasStationDto.setHasSuperPlus(testHasSuperPlus);
	       gasStationDto.setSuperPlusPrice(testSuperPlus);
	       gasStationDto.setHasGas(testHasGas);
	       gasStationDto.setGasPrice(testGas);
	       gasStationDto.setHasMethane(testHasMethane);
	       gasStationDto.setMethanePrice(testMethane);
	       gasStationDto.setCarSharing(testCarSharing);
	
	       CloseableHttpClient GasStationClient = HttpClients.createDefault();
		    HttpPost httpPostGasStation = new HttpPost("http://localhost:8080/gasstation/saveGasStation");
		    
		    String json =String.format("{\"gasStationId\":null,\"gasStationName\":\"%1$s\",\"gasStationAddress\":\"%2$s\","
		    		+      "\"hasDiesel\":%3$b,\"hasSuper\":%4$b,\"hasSuperPlus\":%5$b,\"hasGas\":%6$b,\"hasMethane\":%7$b,"
		    		+ "\"carSharing\":\"%8$s\",\"dieselPrice\":%9$s,\"superPrice\":%10$s,\"superPlusPrice\":%11$s,\"gasPrice\":%12$s,"
		    		+ "\"methanePrice\":%13$s,\"reportUser\":%14$s,\"reportTimestamp\":%15$s,\"lat\":\"%16$s\",\"lon\":\"%17$s\"}",
		    		gasStationDto.getGasStationName(),gasStationDto.getGasStationAddress(),gasStationDto.getHasDiesel(),gasStationDto.getHasSuper(),
		    		gasStationDto.getHasSuperPlus(),gasStationDto.getHasGas(),gasStationDto.getHasMethane(),
		    		gasStationDto.getCarSharing(),gasStationDto.getDieselPrice(),gasStationDto.getSuperPrice(),
		    		gasStationDto.getSuperPlusPrice(),gasStationDto.getGasPrice(),gasStationDto.getMethanePrice(),
		    		gasStationDto.getReportUser(),gasStationDto.getReportTimestamp(),gasStationDto.getLat(),
		    		gasStationDto.getLon()
		    		); 	
		    StringEntity entityGasStation = new StringEntity(json);
		    httpPostGasStation.setEntity(entityGasStation);
		    httpPostGasStation.setHeader("Accept", "application/json");
		    httpPostGasStation.setHeader("Content-type", "application/json");
		 
		    HttpResponse responseGasStation = GasStationClient.execute(httpPostGasStation);		    
		    String jsonFromResponseGasStation = EntityUtils.toString(responseGasStation.getEntity()); 
		    ObjectMapper mapperGasStation = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);     
		    gasStationDto = mapperGasStation.readValue(jsonFromResponseGasStation, GasStationDto.class); 
		    GasStationClient.close();
	  
		 
		
		
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("http://localhost:8080/user/saveUser");
	    
	      json =String.format("{\"userId\":null,\"userName\":\"%1$s\",\"password\":\"%2$s\",\"email\":\"%3$s\",\"reputation\":%4$d,\"admin\":false}",
	    		testName,testPwd,testEmail,testReputation    );
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
	
	@After
	public void Endtest() throws ClientProtocolException, IOException {
		 
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpDelete httpDelete = new HttpDelete("http://localhost:8080/user/deleteUser/"+userDto.getUserId());
		HttpResponse response = client.execute(httpDelete);
		assert(response.getStatusLine().getStatusCode() == 200);
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
		
		String GasType=(gasStationDto.getHasSuperPlus())?"superplus":"null";
		
	   String URI=String.format("http://localhost:8080/gasstation/getGasStationsWithCoordinates/%1$s/%2$s/%3$s/%4$s/%5$s",gasStationDto.getLat(),gasStationDto.getLon(),radius,GasType,gasStationDto.getCarSharing());
	   
		HttpUriRequest httpGet = new HttpGet(URI);
		
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
	    

	    String json =String.format("{\"gasStationId\":%1$s,\"gasStationName\":\"%2$s\",\"gasStationAddress\":\"%3$s\",\"hasDiesel\":%4$s,\"hasSuper\":%5$s,\"hasSuperPlus\":%6$s,\"hasGas\":%7$s,\"hasMethane\":%8$s,\"hasPremiumDiesel\":%9$s,\"carSharing\":\"%10$s\",\"lat\":\"%11$s\",\"lon\":\"%12$s\",\"dieselPrice\":%13$s,\"superPrice\":%14$s,\"superPlusPrice\":%15$s,\"gasPrice\":%16$s,\"methanePrice\":%17$s,\"premiumDieselPrice\":%18$s,\"reportUser\":%19$s,\"userDto\":%20$s,\"reportTimestamp\":%21$s,\"reportDependability\":%22$s}",
	    		                      null,"Q8","Via Federico Spera Foggia Apulia Italy",true,true,true,true,false,false,"Enjoy", 41.4632681,15.5227678,1.4,1.45,0.9,0.7,0.8,0.9,null,null,null,0.0);
	 
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
   	     String URI=String.format("http://localhost:8080/gasstation/getGasStation/%1$d",gasStationDto.getGasStationId());
   	     HttpPost httpResuest = new HttpPost(URI); 
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
	public void testDeleteGasStation() 
	  throws ClientProtocolException, IOException {
		 
		CloseableHttpClient client = HttpClients.createDefault();
	    String URI=String.format("http://localhost:8080/gasstation/deleteGasStation/%1$d",gasStationDto.getGasStationId());
	   	  
		HttpDelete httpDelete = new HttpDelete(URI);
		HttpResponse response = client.execute(httpDelete);
		assert(response.getStatusLine().getStatusCode() == 200);
	    client.close();
	}
	
	
}
