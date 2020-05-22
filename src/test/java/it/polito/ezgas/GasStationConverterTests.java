package it.polito.ezgas;

import static org.junit.Assert.assertEquals;
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
		GasStation gs = new GasStation("GasStation", "Address", true, false, false, false, false, "CarSharing", 0.0, 0.0, 0.5, -1.0, -1.0, -1.0, -1.0, -1, "Timestamp", -1.0);
		Integer id = gs.getGasStationId();
		GasStationDto gsd = new GasStationDto(id, "GasStation", "Address", true, false, false, false, false, "CarSharing", 0.0, 0.0, 0.5, -1.0, -1.0, -1.0, -1.0, -1, "Timestamp", -1.0);
		GasStationDto gsm = gasStationConverter.map(gs, GasStationDto.class);
		
		assertTrue(gsm instanceof GasStationDto); 
		assertTrue(gsm.getGasStationName().contentEquals(gsd.getGasStationName()));
		assertTrue(gsm.getGasStationAddress().contentEquals(gsd.getGasStationAddress()));
		assertEquals(gsd.getHasDiesel(), gsm.getHasDiesel());
		assertEquals(gsd.getHasSuper(), gsm.getHasSuper());
		assertEquals(gsd.getHasSuperPlus(), gsm.getHasSuperPlus());
		assertEquals(gsd.getHasGas(),  gsm.getHasGas());
		assertEquals(gsd.getHasMethane(), gsm.getHasMethane());
		assertTrue(gsm.getCarSharing().contentEquals(gsd.getCarSharing()));
		assertEquals(gsd.getLat(), gsm.getLat(), 0);
		assertEquals(gsd.getLon(), gsm.getLon(), 0);
		assertEquals(gsd.getDieselPrice(), gsm.getDieselPrice(), 0);
		assertEquals(gsd.getSuperPrice(), gsm.getSuperPrice(), 0);
		assertEquals(gsd.getSuperPlusPrice(), gsm.getSuperPlusPrice(), 0);
		assertEquals(gsd.getGasPrice(), gsm.getGasPrice(), 0);
		assertEquals(gsd.getMethanePrice(), gsm.getMethanePrice(), 0);
		assertEquals(gsd.getPriceReportDtos(), gsm.getPriceReportDtos());
		assertEquals(gsd.getUserDto(), gsm.getUserDto());
		assertEquals(gsd.getReportUser(), gsm.getReportUser());
		assertEquals(gsd.getUserDto(), gsm.getUserDto());
		assertTrue(gsd.getReportTimestamp().contentEquals(gsm.getReportTimestamp()));
		assertEquals(gsd.getReportDependability(), gsm.getReportDependability(), 0);
	}
	@Test
	public void testUserConverter1_2() {
		GasStationDto gsd = new GasStationDto(null, "GasStation", "Address", true, false, false, false, false, "CarSharing", 0.0, 0.0, 0.5, -1.0, -1.0, -1.0, -1.0, -1, "Timestamp", -1.0);
		GasStation gsm = gasStationConverter.map(gsd, GasStation.class);
		GasStation gs = new GasStation("GasStation", "Address", true, false, false, false, false, "CarSharing", 0.0, 0.0, 0.5, -1.0, -1.0, -1.0, -1.0, -1, "Timestamp", -1.0);
				
		assertTrue(gsm instanceof GasStation); 
		assertTrue(gsm.getGasStationName().contentEquals(gs.getGasStationName()));
		assertTrue(gsm.getGasStationAddress().contentEquals(gs.getGasStationAddress()));
		assertEquals(gs.getHasDiesel(), gsm.getHasDiesel());
		assertEquals(gs.getHasSuper(), gsm.getHasSuper());
		assertEquals(gs.getHasSuperPlus(), gsm.getHasSuperPlus());
		assertEquals(gs.getHasGas(),  gsm.getHasGas());
		assertEquals(gs.getHasMethane(), gsm.getHasMethane());
		assertTrue(gsm.getCarSharing().contentEquals(gs.getCarSharing()));
		assertEquals(gs.getLat(), gsm.getLat(), 0);
		assertEquals(gs.getLon(), gsm.getLon(), 0);
		assertEquals(gs.getDieselPrice(), gsm.getDieselPrice(), 0);
		assertEquals(gs.getSuperPrice(), gsm.getSuperPrice(), 0);
		assertEquals(gs.getSuperPlusPrice(), gsm.getSuperPlusPrice(), 0);
		assertEquals(gs.getGasPrice(), gsm.getGasPrice(), 0);
		assertEquals(gs.getMethanePrice(), gsm.getMethanePrice(), 0);
		assertEquals(gs.getReportUser(), gsm.getReportUser());
		assertEquals(gs.getUser(), gsm.getUser());
		assertTrue(gs.getReportTimestamp().contentEquals(gsm.getReportTimestamp()));
		assertEquals(gs.getReportDependability(), gsm.getReportDependability(), 0);
	}
}
