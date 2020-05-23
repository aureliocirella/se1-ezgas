package it.polito.ezgas;


import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GasStationRepositoryTests {
	@Autowired 
	GasStationRepository gasStationRepository;
	
	@Test
	public void testGasStationRepository1_1() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		GasStation gs = gasStationRepository.save(new GasStation());
		assertTrue(gasStationRepository.exists(gs.getGasStationId()));
		conn.close();
	}
	
	@Test
	public void testGasStationRepository1_2() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		ArrayList<GasStation> gasStationList= (ArrayList<GasStation>) gasStationRepository.findAll();
		assertTrue(gasStationList instanceof ArrayList<?>);
		conn.close();
	}
	
	@Test
	public void testGasStationRepository1_3() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		GasStation gs = gasStationRepository.save(new GasStation());
		GasStation gasStationList= gasStationRepository.findOne(gs.getGasStationId());
		assertTrue(gasStationList.getGasStationId()==gs.getGasStationId());
		conn.close();
	}
	
	@Test
	public void testGasStationRepository1_4() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		GasStation gs = gasStationRepository.save(new GasStation());
		gasStationRepository.delete(gs.getGasStationId());
		assertFalse(gasStationRepository.exists(gs.getGasStationId()));
		conn.close();
	}
}
