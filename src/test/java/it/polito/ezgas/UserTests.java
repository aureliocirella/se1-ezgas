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

}
