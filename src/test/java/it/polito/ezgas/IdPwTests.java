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
		IdPw gs = new IdPw(); 
		String pass = gs.getPw(); 
		assertNull(pass);
	}
	
	
	@Test
	public void testIdPw1_3(){
		String inputpass = "testpass";
		IdPw gs = new IdPw(); 	 
		gs.setPw(inputpass);
		String outputpass = gs.getPw(); 
		assertTrue(outputpass == inputpass);
	}
	
	@Test
	public void testIdPw1_4(){
		String inputuser = "testuser";
		IdPw gs = new IdPw(); 
		gs.setUser(inputuser);
		String outputpass = gs.getUser(); 
		assertTrue(outputpass == inputuser);
	}
	

	
	@Test
	public void testIdPw1_5(){
		String inputuser = "";
		IdPw gs = new IdPw(); 	 
		gs.setUser(inputuser);
		String outputuser = gs.getUser(); 
		assertTrue(outputuser == inputuser);
	}
	@Test
	public void testIdPw1_6(){
		String inputuser = null;
		IdPw gs = new IdPw(); 	 
		gs.setUser(inputuser);
		String outputuser = gs.getUser(); 
		assertNull(outputuser);
	}
	
	@Test
	public void testIdPw1_7(){
		String inputpass = "";
		IdPw gs = new IdPw(); 	 
		gs.setPw(inputpass);
		String outputpass = gs.getPw(); 
		assertTrue(outputpass == inputpass);
	}
	@Test
	public void testIdPw1_8(){
		String inputpass = null;
		IdPw gs = new IdPw("testuser",inputpass);  
		gs.setPw(inputpass);
		String outputpass = gs.getPw(); 
		assertNull(outputpass);
	}
	
	 
	
}
