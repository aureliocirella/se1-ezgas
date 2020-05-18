package it.polito.ezgas;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import it.polito.ezgas.entity.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
	
	@Test
	public void testUser1_1(){
		User gs = new User(); 
		gs.setUserId(Integer.MAX_VALUE+1);
		Integer id = gs.getUserId(); 
		assertTrue(id == Integer.MIN_VALUE);
	}
	
	@Test
	public void testUser1_2(){
		User gs = new User(); 
		gs.setUserId(Integer.MAX_VALUE+1);
		Integer id = gs.getUserId(); 
		assertTrue(id == Integer.MIN_VALUE);
	}
	
	@Test
	public void testUser1_3(){
		User gs = new User(); 
		gs.setUserId(4);
		Integer id = gs.getUserId(); 
		assertTrue(id == 4);
	}
	
	@Test
	public void testUser1_4(){
		User gs = new User(); 
		gs.setUserId(-4);
		Integer id = gs.getUserId(); 
		assertTrue(id == -4);
	}
	@Test
	public void testUser1_5(){
		User gs = new User(); 
		
		Integer id = gs.getUserId(); 
		assertNull(id);
	}
	@Test
	public void testUser1_6(){
		User gs = new User(); 
		
		String pass = gs.getPassword(); 
		assertNull(pass);
	}
	
	@Test
	public void testUser1_7(){
		String inputpass = "testpass";
		User gs = new User(); 
		gs.setPassword(inputpass);
		String outputpass = gs.getPassword(); 
		assertTrue(outputpass == inputpass);
	}
	
	@Test
	public void testUser1_8() {
		User us = new User( "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		String email = us.getEmail();
		org.junit.Assert.assertTrue (email.equals("mario.rossi@polito.it"));
	}

	@Test
	public void testUser1_9() {
		User us  = new User( "Mario Rossi", "MR", "", 1);
		String email = us.getEmail();
		org.junit.Assert.assertTrue (email.equals(""));
	}

	@Test
	public void testUser1_10() {
		User us = new User( "Mario Rossi", "MR", null, 1);
		String email = us.getEmail();
		org.junit.Assert.assertNull (email);
	}

	@Test
	public void testUser1_11() {
		User us = new User( "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		us.setEmail("luigi.verdi@polito.it");
		String email = us.getEmail();
		org.junit.Assert.assertTrue (email.equals("luigi.verdi@polito.it"));
	}

	@Test
	public void testUser1_12() {
		User us = new User( "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		us.setEmail("");
		String email = us.getEmail();
		org.junit.Assert.assertTrue (email.equals(""));
	}

	@Test
	public void testUser1_13() {
		User us = new User( "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		us.setEmail(null);
		String email = us.getEmail();
		org.junit.Assert.assertNull (email);
	}

	@Test
	public void testUser2_1() {
		User us = new User( "Mario Rossi", "MR", "mario.rossi@polito.it", null);
		Integer reputation = us.getReputation();
		org.junit.Assert.assertTrue(reputation == null);
	}

	@Test
	public void testUser2_2() {
		User us = new User("Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		Integer reputation = us.getReputation();
		org.junit.Assert.assertTrue(reputation == 1);
	}

	@Test
	public void testUser2_3() {
		User us = new User( "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		us.setReputation(6);
		Integer reputation = us.getReputation();
		org.junit.Assert.assertTrue(reputation > 5);
	}

	@Test
	public void testUser2_4() {
		User us = new User( "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		us.setReputation(-6);
		Integer reputation = us.getReputation();
		org.junit.Assert.assertTrue(reputation < -5);
	}

	@Test
	public void testUser2_5() {
		User us = new User( "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		us.setReputation(-1);
		Integer reputation = us.getReputation();
		org.junit.Assert.assertTrue(reputation == -1);
	}
	

}
