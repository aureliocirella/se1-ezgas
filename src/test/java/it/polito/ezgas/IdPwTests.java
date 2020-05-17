package it.polito.ezgas;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.IdPw;
 
 

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdPwTests {

	
	@Test
	public void testIdPw1_1(){
		IdPw gs = new IdPw(); 
		
		String user = gs.getUser(); 
		assertNull(user);
	}
	
	
	@Test
	public void testIdPw1_2(){
		String inputpass = "testpass";
		IdPw gs = new IdPw(); 
	 
		gs.setUser(inputpass);
		String outputpass = gs.getUser(); 
		assertTrue(outputpass == inputpass);
	}
	
	@Test
	public void testIdPw1_3(){
		IdPw gs = new IdPw(); 
		
		String user = gs.getUser(); 
		assertNull(user);
	}
	
	@Test
	public void testIdPw1_4(){
		IdPw gs = new IdPw(); 
		
		String user = gs.getPw(); 
		assertNull(user);
	}
	
	
	@Test
	public void testIdPw1_5(){
		String inputpass = "testpass";
		IdPw gs = new IdPw(); 
	 
		gs.setPw(inputpass);
		String outputpass = gs.getPw(); 
		assertTrue(outputpass == inputpass);
	}
	
	
	
}
