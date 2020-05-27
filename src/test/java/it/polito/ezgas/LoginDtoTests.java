package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import it.polito.ezgas.dto.LoginDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginDtoTests {

	@Test
	public void testLoginDto1_1() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		String name = log.getUserName();
		org.junit.Assert.assertTrue (name.equals("Mario Rossi"));
	}

	@Test
	public void testLoginDto1_2() {
		LoginDto log = new LoginDto(1, "", "MR", "mario.rossi@polito.it", 1);
		String name = log.getUserName();
		org.junit.Assert.assertTrue (name.equals(""));
	}

	@Test
	public void testLoginDto1_3() {
		LoginDto log = new LoginDto(1, null, "MR", "mario.rossi@polito.it", 1);
		String name = log.getUserName();
		org.junit.Assert.assertNull (name);
	}

	@Test
	public void testLoginDto1_4() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		log.setUserName("Luigi Verdi");
		String name = log.getUserName();
		org.junit.Assert.assertTrue (name.equals("Luigi Verdi"));
	}

	@Test
	public void testLoginDto1_5() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		log.setUserName("");
		String name = log.getUserName();
		org.junit.Assert.assertTrue (name.equals(""));
	}

	@Test
	public void testLoginDto1_6() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		log.setUserName(null);
		String name = log.getUserName();
		org.junit.Assert.assertNull (name);
	}

	@Test
	public void testLoginDto2_1() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		Boolean admin = log.getAdmin();
		org.junit.Assert.assertNull(admin);
	}

	@Test
	public void testLoginDto2_2() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		log.setAdmin(null);
		Boolean admin = log.getAdmin();
		org.junit.Assert.assertNull(admin);
	}

	@Test
	public void testLoginDto2_3() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		log.setAdmin(false);
		Boolean admin = log.getAdmin();
		org.junit.Assert.assertFalse(admin);
	}

	@Test
	public void testLoginDto2_4() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		log.setAdmin(true);
		Boolean admin = log.getAdmin();
		org.junit.Assert.assertTrue (admin);
	}
	
}
