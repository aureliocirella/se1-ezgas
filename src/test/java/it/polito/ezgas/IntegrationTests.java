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
     @Before
	 public void setUp() {
		try {
			 
			Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
			if(userRepository!=null)
			{
			 
			if(userRepository.findByEmail( "Winters@ezgaz.it" ).isEmpty())
			{   
				UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
				UserDto user = new UserDto(1, "Raquel", "Winters", "Winters@ezgaz.it", 1);
				  userImpl.saveUser(user); 
			}
			}
			conn.close(); 
	
		}catch (Exception e) 
		{ 
			fail ("SetUp Error");
			}
	}
	 @After
	 public void EndTest() {
		try {
			 
			Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
			if(userRepository!=null)
			{
				ArrayList<User> foundusers=	userRepository.findByEmail( "Winters@ezgaz.it" );
				if(foundusers.size()>0)
				 userRepository.delete(foundusers);
			
			
			  foundusers=	userRepository.findByEmail( "mario.rossi@polito.it" );
			if(foundusers.size()>0)
			 userRepository.delete(foundusers);
			
			}
			conn.close(); 
	
		}catch (Exception e) 
		{ 
			fail ("SetUp Error");
			}
	}
	
	@Test
	public void testIntegration1_1() throws SQLException {
	 
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
    	UserDto usw = userImpl.saveUser(us); 
		assertNotNull(usw);
	 
		conn.close(); 
		
		
	}
	
	@Test
	public void testIntegration1_2() throws SQLException, InvalidUserException {
		 
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
    	UserDto usw = userImpl.saveUser(us); 
    	Boolean deleted = userImpl.deleteUser(usw.getUserId()); 
		assertTrue(deleted);
	 
		conn.close(); 
		
		
	}

	@Test
	public void testIntegration1_3() throws SQLException, InvalidUserException {
		 
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
    	UserDto usw = userImpl.saveUser(us); 
    	UserDto usDto = userImpl.getUserById(usw.getUserId()); 
		assertNotNull(usDto);
	 
		conn.close(); 
		
		
	}

	@Test
	public void testIntegration1_4() throws SQLException, InvalidUserException {
		 
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
    	ArrayList<UserDto> usDto = (ArrayList<UserDto>) userImpl.getAllUsers(); 
		assertTrue(!usDto.isEmpty());
	 
		conn.close(); 
		
		
	}

	@Test
	public void testIntegration1_5() throws SQLException, InvalidUserException {
	//	setUp() ;
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);

		User userfound=userRepository.findByEmail( "Winters@ezgaz.it" ).get(0);
		Integer id=	userfound.getUserId();
		Integer PreviousReputation=userfound.getReputation();
		System.out.println(id);
		Integer currentReputation=  userImpl.increaseUserReputation(id ); 
		PreviousReputation++;
		assertEquals(currentReputation,PreviousReputation);
	 
		conn.close(); 
	//	EndTest();
		
	}

	@Test
	public void testIntegration1_6() throws SQLException, InvalidUserException {
	//	setUp() ;
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);

		User userfound=userRepository.findByEmail( "Winters@ezgaz.it" ).get(0);
		Integer id=	userfound.getUserId();
		Integer PreviousReputation=userfound.getReputation();
		System.out.println(id);
	    System.out.println("reputationis : "+PreviousReputation);
		Integer currentReputation=  userImpl.decreaseUserReputation(id ); 
		System.out.println("reputation after is : "+PreviousReputation);
		PreviousReputation--;
		assertEquals(currentReputation,PreviousReputation);
	 
		conn.close(); 
	//	EndTest();
		
	}

	@Test
	public void testIntegration1_7() throws SQLException, InvalidUserException {
		 
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		
		if(userRepository.findByAdmin(true).isEmpty())
		{
			User user= new User("admin", "admin", "admin@ezgas.com", 5);
			user.setAdmin(true);
			user.setUserId(1); 
			userRepository.save(user); 
		}
		IdPw credentials=new IdPw("admin@ezgas.com","admin");
		LoginDto usDto;
		try {
			usDto = userImpl.login(credentials);
			assertNotNull(usDto);
		} catch (InvalidLoginDataException e) {
			fail("Login failed for " +credentials.getUser());
			 
		} 
    		
		conn.close(); 
		//EndTest(); 
		
	}

	@Autowired 
	GasStationRepository gasStationRepository;
	@Autowired
	GasStationConverter gasStationConverter; 
	
	@Test
	public void testIntegration1_8() throws SQLException, PriceException, GPSDataException, InvalidGasStationException {

		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
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
		

		conn.close(); 
	}
	

}
