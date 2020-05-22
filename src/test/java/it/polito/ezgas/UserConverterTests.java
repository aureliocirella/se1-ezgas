package it.polito.ezgas;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserConverterTests {
	
	@Autowired
	UserConverter userConverter; 
	@Test
	public void testUserConverter1_1() {
		User user = new User(); 
		assertTrue(userConverter.map(user, UserDto.class) instanceof UserDto); 
	}
	@Test
	public void testUserConverter1_2() {
		UserDto userDto = new UserDto(); 
		assertTrue(userConverter.map(userDto, User.class) instanceof User); 
	}
	
	

}
