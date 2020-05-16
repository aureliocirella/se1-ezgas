package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.service.impl.GasStationServiceimpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EZGasApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testT1_1() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		String name = log.getUserName();
		org.junit.Assert.assertTrue (name.equals("Mario Rossi"));
	}

	@Test
	public void testT1_2() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		log.setUserName("Luigi Verdi");
		String name = log.getUserName();
		org.junit.Assert.assertTrue (name.equals("Luigi Verdi"));
	}

	@Test
	public void testT2_1() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		Boolean admin = log.getAdmin();
		org.junit.Assert.assertFalse(admin);
	}

	@Test
	public void testT2_2() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		log.setAdmin(false);
		Boolean admin = log.getAdmin();
		org.junit.Assert.assertFalse(admin);
	}

	@Test
	public void testT2_3() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		log.setAdmin(true);
		Boolean admin = log.getAdmin();
		org.junit.Assert.assertTrue (admin);
	}

	@Test
	public void testT3_1() {
		LoginDto log = new LoginDto(1, "Mario Rossi", "MR", "mario.rossi@polito.it", 1);
		Integer reputation = log.getReputation();
		assert (reputation == 1);
	}

	@Test
	public void testT4_1() {
		double res = it.polito.ezgas.service.impl.GasStationServiceimpl.distance(1.0, 1.0, 1.0, 1.0);
		org.junit.Assert.assertEquals (res, 0.0, 0.00001);
	}

	@Test
	public void testT4_2() {
		double res = it.polito.ezgas.service.impl.GasStationServiceimpl.distance(0.0, 180.0, 0.0, -180.0);
		org.junit.Assert.assertEquals (res, 0.0, 0.00001);
	}

	@Test
	public void testT4_3() {
		double res = it.polito.ezgas.service.impl.GasStationServiceimpl.distance(0.0, 180.0, 0.0, 540.0);
		org.junit.Assert.assertEquals (res, 0.0, 0.00001);
	}

	@Test
	public void testT4_4() {
		double res = it.polito.ezgas.service.impl.GasStationServiceimpl.distance(0.0, 1.0, 0.0, 0.0);
		org.junit.Assert.assertNotEquals (res, 0.0, 0.00001);
	}
	
}
