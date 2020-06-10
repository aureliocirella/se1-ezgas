package it.polito.ezgas;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import it.polito.ezgas.entity.GasStation;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GasStationTests {
	
	@Test
	public void testGasStation1_1(){
		GasStation gs = new GasStation(); 
		gs.setGasStationId(Integer.MAX_VALUE+1);
		Integer id = gs.getGasStationId(); 
		assertTrue(id == Integer.MIN_VALUE);
	}
	
	@Test
	public void testGasStation1_2(){
		GasStation gs = new GasStation(); 
		gs.setGasStationId(Integer.MIN_VALUE-1);
		Integer id = gs.getGasStationId(); 
		assertTrue(id == Integer.MAX_VALUE);
	}
	
	@Test
	public void testGasStation1_3(){
		GasStation gs = new GasStation(); 
		gs.setGasStationId(4);
		Integer id = gs.getGasStationId(); 
		assertTrue(id == 4);
	}
	
	@Test
	public void testGasStation1_4(){
		GasStation gs = new GasStation(); 
		gs.setGasStationId(-4);
		Integer id = gs.getGasStationId(); 
		assertTrue(id == -4);
	}
	
	public void testGasStation1_5(){
		GasStation gs = new GasStation(); 
		Integer id = gs.getGasStationId(); 
		assertNull(id);
	}
	
	public void testGasStation1_6(){
		GasStation gs = new GasStation(); 
		gs.setGasStationName("Agip");
		String gsname = gs.getGasStationName();
		assertTrue(gsname.contentEquals("Agip"));
	}
	
	public void testGasStation1_7(){
		GasStation gs = new GasStation(); 
		gs.setGasStationName("");
		String gsname = gs.getGasStationName();
		assertTrue(gsname.contentEquals(""));
	}
	
	public void testGasStation1_8(){
		GasStation gs = new GasStation(); 
		String gsname = gs.getGasStationName();
		assertNull(gsname);
	}

	@Test
	public void testGasStation2_1(){
		GasStation gs = new GasStation(); 
		gs.setReportDependability(Double.MAX_VALUE+1);
		Double id = gs.getReportDependability(); 
		assertTrue(id == Double.MAX_VALUE);
	}

	@Test
	public void testGasStation2_2(){
		GasStation gs = new GasStation(); 
		gs.setReportDependability(4.2);
		Double id = gs.getReportDependability(); 
		assertTrue(id == 4.2);
	}
	
	@Test
	public void testGasStation2_3(){
		GasStation gs = new GasStation(); 
		gs.setReportDependability(-4.2);
		Double id = gs.getReportDependability(); 
		assertTrue(id == -4.2);
	}
	
	@Test
	public void testGasStation2_4(){
		GasStation gs = new GasStation(); 
		//gs.setReportDependability();
		Double id = gs.getReportDependability(); 
		assertEquals(Double.valueOf(0.0), id);
	}
}
