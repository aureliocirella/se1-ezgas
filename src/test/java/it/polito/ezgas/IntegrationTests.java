package it.polito.ezgas;




import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.UserDto;
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

}
