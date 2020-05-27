package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GasStationServiceimplTests {

	@Test
	public void testGasStationServiceimpl1_1() {
		double res = it.polito.ezgas.service.impl.GasStationServiceimpl.distance(1.0, 1.0, 1.0, 1.0);
		org.junit.Assert.assertEquals (res, 0.0, 0.00001);
	}

	@Test
	public void testGasStationServiceimpl1_2() {
		double res = it.polito.ezgas.service.impl.GasStationServiceimpl.distance(0.0, 180.0, 0.0, -180.0);
		org.junit.Assert.assertEquals (res, 0.0, 0.00001);
	}

	@Test
	public void testGasStationServiceimpl1_3() {
		double res = it.polito.ezgas.service.impl.GasStationServiceimpl.distance(0.0, 180.0, 0.0, 540.0);
		org.junit.Assert.assertEquals (res, 0.0, 0.00001);
	}

	@Test
	public void testGasStationServiceimpl1_4() {
		double res = it.polito.ezgas.service.impl.GasStationServiceimpl.distance(0.0, 1.0, 0.0, 0.0);
		org.junit.Assert.assertNotEquals (res, 0.0, 0.00001);
	}

	@Test
	public void testGasStationServiceimpl1_5() {
		double res = it.polito.ezgas.service.impl.GasStationServiceimpl.distance(90.0, 0.0, -90.0, 0.0);
		org.junit.Assert.assertNotEquals (res, 0.0, 0.00001);
	}

	@Test
	public void testGasStationServiceimpl1_6() {
		double res = it.polito.ezgas.service.impl.GasStationServiceimpl.distance(270.0, 1.0, -90.0, 0.0);
		org.junit.Assert.assertEquals (res, 0.0, 0.00001);
	}

	@Test
	public void testGasStationServiceimpl1_7() {
		double res = it.polito.ezgas.service.impl.GasStationServiceimpl.distance(Double.MAX_VALUE+1000000.0, 0.0, 0.0, 0.0);
		org.junit.Assert.assertNotEquals (res, 0.0, 0.00001);
	}

	@Test
	public void testGasStationServiceimpl1_8() {
		try {
			it.polito.ezgas.service.impl.GasStationServiceimpl.distance((Double) null, 0.0, 0.0, 0.0);
			Assert.fail("Expected NullPointerException");
		} catch (Exception NullPointerException) {
			Assert.assertTrue(true);
		}
	}
	
	
	
}
