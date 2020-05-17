package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import it.polito.ezgas.dto.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDtoTests {

	@Test
	public void testUserDto1_1() {
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		String email = us.getEmail();
		org.junit.Assert.assertTrue (email.equals("mario.rossi@polito.it"));
	}

	@Test
	public void testUserDto1_2() {
		UserDto us  = new UserDto(1, "Mario Rossi", "MR", "", 1);
		String email = us.getEmail();
		org.junit.Assert.assertTrue (email.equals(""));
	}

	@Test
	public void testUserDto1_3() {
		UserDto us = new UserDto(1, "Mario Rossi", "MR", null, 1);
		String email = us.getEmail();
		org.junit.Assert.assertNull (email);
	}

	@Test
	public void testUserDto1_4() {
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		us.setEmail("luigi.verdi@polito.it");
		String email = us.getEmail();
		org.junit.Assert.assertTrue (email.equals("luigi.verdi@polito.it"));
	}

	@Test
	public void testUserDto1_5() {
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		us.setEmail("");
		String email = us.getEmail();
		org.junit.Assert.assertTrue (email.equals(""));
	}

	@Test
	public void testUserDto1_6() {
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		us.setEmail(null);
		String email = us.getEmail();
		org.junit.Assert.assertNull (email);
	}

	@Test
	public void testUserDto2_1() {
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", null);
		Integer reputation = us.getReputation();
		org.junit.Assert.assertTrue(reputation == null);
	}

	@Test
	public void testUserDto2_2() {
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		Integer reputation = us.getReputation();
		org.junit.Assert.assertTrue(reputation == 1);
	}

	@Test
	public void testUserDto2_3() {
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		us.setReputation(6);
		Integer reputation = us.getReputation();
		org.junit.Assert.assertTrue(reputation > 5);
	}

	@Test
	public void testUserDto2_4() {
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		us.setReputation(-6);
		Integer reputation = us.getReputation();
		org.junit.Assert.assertTrue(reputation < -5);
	}

	@Test
	public void testUserDto2_5() {
		UserDto us = new UserDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		us.setReputation(-1);
		Integer reputation = us.getReputation();
		org.junit.Assert.assertTrue(reputation == -1);
	}
}
