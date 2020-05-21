package it.polito.ezgas;


import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
	
	@Autowired 
	UserRepository userRepository;
	@Test
	public void testUserRepository1_1() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		User u1_1 = userRepository.save(new User());
		assertTrue(userRepository.exists(u1_1.getUserId()));
		conn.close();
	}
	public void testUserRepository1_2() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		ArrayList<User> userList= (ArrayList<User>) userRepository.findAll();
		assertTrue(userList instanceof ArrayList<?>);
		conn.close();
	}
	public void testUserRepository1_3() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		User u1 = userRepository.save(new User());
		User userList= userRepository.findOne(u1.getUserId());
		assertTrue(userList.getUserId()==u1.getUserId());
		conn.close();
	}
	public void testUserRepository1_4() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		User u1 = userRepository.save(new User());
		userRepository.delete(u1.getUserId());
		assertFalse(userRepository.exists(u1.getUserId()));
		conn.close();
	}

}
