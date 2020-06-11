package it.polito.ezgas;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.GasStationServiceimpl;
import it.polito.ezgas.service.impl.UserServiceimpl;

@RunWith(SpringRunner.class)
@SpringBootTest


public class IntegrationTests {
	@Autowired 
	UserRepository userRepository;
	@Autowired
	UserConverter userConverter; 
	@Autowired 
	GasStationRepository gasStationRepository;
	@Autowired
	GasStationConverter gasStationConverter; 
	
	Integer oldUser; 
	
	@Autowired 
	UserServiceimpl userServiceImpl;
	
	@Autowired 
	GasStationServiceimpl gasStationServiceImpl; 
	
	
    private String testName = "Raquel";
    private String testEmail = "Winters@ezgaz.it";
    private String testPwd = "Winters";
    private String testName2 = "Hancock";
    private String testEmail2 = "Hancock@ezgas.com";
    private String testPwd2 = "Layla";
    private String testNameAdmin = "admin";
    private String testEmailAdmin = "admin@ezgas.com";
    private String testPwdAdmin = "testPwd_admin";
    private Integer testReputation = 1;
	
	private GasStationDto testGasStation;
    private String testGasStationName = "testGasStation";
    private String testGasStationAddress = "testAddress";
    private double testLat = 60.0;
    private double testLon = 60.0;
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
    private String testCarSharing = "testCarSharing";
    
    private GasStationDto testGasStation2;
    private String testGasStationName2 = "testGasStation2";
    private String testGasStationAddress2 = "testAddress2";
    private double testLat2 = 60.005;
    private double testLon2 = 60.005;
    private String testCarSharing2 = "testCarSharing2";

    
     @Before
	 public void setUp() {
		try {
			   testGasStation = new GasStationDto();
		        testGasStation.setGasStationName(testGasStationName);
		        testGasStation.setGasStationAddress(testGasStationAddress);
		        testGasStation.setLat(testLat);
		        testGasStation.setLon(testLon);
		        testGasStation.setHasDiesel(testHasDiesel);
		        testGasStation.setDieselPrice(testDiesel);
		        testGasStation.setHasSuper(testHasSuper);
		        testGasStation.setSuperPrice(testSuper);
		        testGasStation.setHasSuperPlus(testHasSuperPlus);
		        testGasStation.setSuperPlusPrice(testSuperPlus);
		        testGasStation.setHasGas(testHasGas);
		        testGasStation.setGasPrice(testGas);
		        testGasStation.setHasMethane(testHasMethane);
		        testGasStation.setMethanePrice(testMethane);
		        testGasStation.setCarSharing(testCarSharing);
		        testGasStation2 = new GasStationDto();
		        testGasStation2.setGasStationName(testGasStationName2);
		        testGasStation2.setGasStationAddress(testGasStationAddress2);
		        testGasStation2.setLat(testLat2);
		        testGasStation2.setLon(testLon2);
		        testGasStation2.setHasDiesel(testHasDiesel);
		        testGasStation2.setDieselPrice(testDiesel);
		        testGasStation2.setHasSuper(testHasSuper);
		        testGasStation2.setSuperPrice(testSuper);
		        testGasStation2.setHasSuperPlus(testHasSuperPlus);
		        testGasStation2.setSuperPlusPrice(testSuperPlus);
		        testGasStation2.setHasGas(testHasGas);
		        testGasStation2.setGasPrice(testGas);
		        testGasStation2.setHasMethane(testHasMethane);
		        testGasStation2.setMethanePrice(testMethane);
		        testGasStation2.setCarSharing(testCarSharing2);
		        GasStation gasstationdemo= gasStationConverter.map(testGasStation, GasStation.class);
		        gasStationRepository.save(gasstationdemo);
		        testGasStation.setGasStationId(gasstationdemo.getGasStationId());
			    GasStation gasstationdemo2= gasStationConverter.map(testGasStation2, GasStation.class);
			    gasStationRepository.save(gasstationdemo2);
			    testGasStation2.setGasStationId(gasstationdemo2.getGasStationId());
		 	if(userRepository!=null)
			{
			 
				if(userRepository.findByEmail(testEmail).isEmpty())
				{   
					UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
				    UserDto user = new UserDto(1,testName ,testPwd , testEmail,testReputation);
					UserDto u = userImpl.saveUser(user); 
				 	 
					gasStationServiceImpl.setReport(testGasStation.getGasStationId(),testGasStation.getDieselPrice(),testGasStation.getSuperPrice()
							,testGasStation.getSuperPlusPrice(),testGasStation.getGasPrice(),testGasStation.getMethanePrice(),u.getUserId());
 	 	
				}
				if(userRepository.findByEmail( testEmail2 ).isEmpty())
				{   
					UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
				    UserDto user = new UserDto(2,testName2 ,testPwd2 , testEmail2,testReputation);
					UserDto u = userImpl.saveUser(user); 
				 	 
					
				}

				
				if(userRepository.findByEmail( "admin@ezgas.com" ).isEmpty())
				{   
					UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
					UserDto user = new UserDto(3, testNameAdmin, testPwdAdmin, testEmailAdmin, 5);
					UserDto u = userImpl.saveUser(user); 
				 	 
					gasStationServiceImpl.setReport(testGasStation2.getGasStationId(),testGasStation2.getDieselPrice(),testGasStation2.getSuperPrice()
							,testGasStation2.getSuperPlusPrice(),testGasStation2.getGasPrice(),testGasStation2.getMethanePrice(),u.getUserId());
 	 	
				}
				
			}
			 
	
		}catch (Exception e) 
		{ 
		 fail("SetUp Error");
			}
	}
	 @After 
	 public void EndTest() {
		try {
			 
 
			if(userRepository!=null)
			{
							 
			 GasStation foundgas1 =	gasStationRepository.findOne(testGasStation.getGasStationId() );
			 GasStation foundgas2 =	gasStationRepository.findOne(testGasStation2.getGasStationId() );
				if(foundgas1!=null) 
				gasStationServiceImpl.deleteGasStation(foundgas1.getGasStationId() );
				if(foundgas2!=null) 
				gasStationServiceImpl.deleteGasStation(foundgas2.getGasStationId() );
				
				ArrayList<User> foundusers=	userRepository.findByEmail( testEmail);
				if(foundusers.size()>0)
				 userRepository.delete(foundusers);
		 
			
			}
			 
	
		}catch (Exception e) 
		{ 
			 fail ("SetUp Error");
			}
	}
	
	@Test
	public void testIntegration1_1() throws SQLException {
	 
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		UserDto us = new UserDto(1,testName ,testPwd , testEmail,testReputation);
    	UserDto usw = userImpl.saveUser(us); 
		assertNotNull(usw);
 
	}
	
	@Test
	public void testIntegration1_2() throws SQLException, InvalidUserException {

		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		User userfound=userRepository.findByEmail( testEmail2 ).get(0);
	 
		Integer id= userfound.getUserId();
    	Boolean deleted = userImpl.deleteUser(id); 
		assertTrue(deleted);
	
	}

	@Test
	public void testIntegration1_3() throws SQLException, InvalidUserException {
		 
		 
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		User userfound=userRepository.findByEmail( testEmail  ).get(0);
    	UserDto usDto = userImpl.getUserById(userfound.getUserId()); 
		assertEquals(usDto.getEmail(), userfound.getEmail());
	 
		
		
	}

	@Test
	public void testIntegration1_4() throws SQLException, InvalidUserException {
		 
	    UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
    	ArrayList<UserDto> usDto = (ArrayList<UserDto>) userImpl.getAllUsers(); 
		assertTrue(!usDto.isEmpty());
	 
 
		
		
	}

	@Test
	public void testIntegration1_5() throws SQLException, InvalidUserException {
  	
		UserServiceimpl userImpl = userServiceImpl;
		User userfound=userRepository.findByEmail( testEmailAdmin ).get(0);
		Integer id=	userfound.getUserId();
		Integer PreviousReputation=userfound.getReputation();
 
		Integer currentReputation=  userImpl.increaseUserReputation(id); 
		PreviousReputation= ( PreviousReputation>5)?5:PreviousReputation+1;
		 
		assertEquals(currentReputation,PreviousReputation);
	 
	 
		
	}

	@Test
	public void testIntegration1_6() throws SQLException, InvalidUserException {
 
		User userfound=userRepository.findByEmail( testEmailAdmin ).get(0);
		Integer id=	userfound.getUserId();
		Integer PreviousReputation=userfound.getReputation();
 
		Integer currentReputation=  userServiceImpl.decreaseUserReputation(id ); 
 
		if(PreviousReputation-- <-5)
		  PreviousReputation = -5; 
	
		assertEquals(currentReputation,PreviousReputation);
	 
	 
	}

	@Test
	public void testIntegration1_7() throws SQLException, InvalidUserException {
	 	User user= new User(testNameAdmin,testPwdAdmin, testEmailAdmin, 5);
		user.setAdmin(true);
		user.setUserId(1);
		ArrayList<User> us = userRepository.findByAdmin(true);
		
		if(!us.contains(user))
		{
			userRepository.save(user); 
		}
		IdPw credentials=new IdPw(testEmailAdmin,testPwdAdmin);
		LoginDto usDto;
		try {
			usDto = userServiceImpl.login(credentials);
			assertNotNull(usDto);
		} catch (InvalidLoginDataException e) {
			fail("Login failed for " +credentials.getUser());
			 
		} 
    		
		 
	}


	
	@Test
	public void testIntegration1_8() throws SQLException, PriceException, GPSDataException, InvalidGasStationException {

	   GasStationServiceimpl gasStationImpl = new GasStationServiceimpl(gasStationRepository, userRepository, gasStationConverter);
		GasStationDto gs = new GasStationDto(1, "GSName", "Address", true, false, false, false, false, "SharingCompany", 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, null, null, 0.0);
		GasStationDto gsw = gasStationImpl.saveGasStation(gs); 
		assertNotNull(gsw);

    	GasStationDto gsDto = gasStationImpl.getGasStationById(gsw.getGasStationId()); 
		assertNotNull(gsDto);
		
    	ArrayList<GasStationDto> gsDtoList = (ArrayList<GasStationDto>) gasStationImpl.getAllGasStations(); 
		assertTrue(!gsDtoList.isEmpty());

    	Boolean deleted = gasStationImpl.deleteGasStation(gsw.getGasStationId()); 
		assertTrue(deleted);
		 
	}
	

}
