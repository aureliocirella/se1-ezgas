package it.polito.ezgas;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.UserServiceimpl;

@RunWith(SpringRunner.class)
@SpringBootTest


public class IntegrationTests {
	@Autowired 
	UserRepository userRepository;
	@Autowired
	UserConverter userConverter; 
	
	@Test
	public void testIntegration1_1() throws SQLException {
	 
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
    	UserDto usw = userImpl.saveUser(us); 
		assertNotNull(usw);
	 
		conn.close(); 
		
		
	}
	public void testIntegration1_2() throws SQLException, InvalidUserException {
		 
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
    	UserDto usw = userImpl.saveUser(us); 
    	Boolean deleted = userImpl.deleteUser(usw.getUserId()); 
		assertTrue(deleted);
	 
		conn.close(); 
		
		
	}
	
	public void testIntegration1_3() throws SQLException, InvalidUserException {
		 
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
    	UserDto usw = userImpl.saveUser(us); 
    	UserDto usDto = userImpl.getUserById(usw.getUserId()); 
		assertNotNull(usDto);
	 
		conn.close(); 
		
		
	}
	
	public void testIntegration1_4() throws SQLException, InvalidUserException {
		 
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
    	ArrayList<UserDto> usDto = (ArrayList<UserDto>) userImpl.getAllUsers(); 
		assertTrue(!usDto.isEmpty());
	 
		conn.close(); 
		
		
	}
	public void testIntegration1_5() throws SQLException, InvalidUserException {
		 
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		Integer PreviousReputation=1;
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it",PreviousReputation);
		Integer currentReputation=  userImpl.increaseUserReputation( us.getUserId()); 
		PreviousReputation++;
		assertEquals(currentReputation,PreviousReputation);
	 
		conn.close(); 
		
		
	}
	public void testIntegration1_6() throws SQLException, InvalidUserException {
		 
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		Integer PreviousReputation=1;
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it",PreviousReputation);
		Integer currentReputation=  userImpl.decreaseUserReputation( us.getUserId()); 
		PreviousReputation--;
		assertEquals(currentReputation,PreviousReputation);
	 
		conn.close(); 
		
		
	}
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
		
		
	}

}
