package it.polito.ezgas;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GasStationConverterTests {
	
	@Autowired
	GasStationConverter gasStationConverter; 
	@Test
	public void testUserConverter1_1() {
		GasStation gasStation = new GasStation(); 
		assertTrue(gasStationConverter.map(gasStation, GasStationDto.class) instanceof GasStationDto); 
	}
	public void testUserConverter1_2() {
		GasStationDto gasStationDto = new GasStationDto(); 
		assertTrue(gasStationConverter.map(gasStationDto, GasStation.class) instanceof GasStation); 
	}
	
	

}
