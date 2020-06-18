package it.polito.ezgas;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.PriceReportDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceReportDtoTests {

	@Test
	public void testPriceReportDto1_1() {
		PriceReportDto prd = new PriceReportDto(null, 1.2, 1.1, 1.5, 0.8, 0.5, 1.4, null);
		Integer userId = prd.getUserId();
		Assert.assertTrue(userId == null);
	}
	
	@Test
	public void testPriceReportDto1_2() {
		PriceReportDto prd = new PriceReportDto(null, 1.2, 1.1, 1.5, 0.8, 0.5, 1.4, 1);
		Integer userId = prd.getUserId();
		Assert.assertTrue(userId == 1);
	}
	
	@Test
	public void testPriceReportDto1_3() {
		PriceReportDto prd = new PriceReportDto(null, 1.2, 1.1, 1.5, 0.8, 0.5, 1.4, -1);
		Integer userId = prd.getUserId();
		Assert.assertTrue(userId == -1);
	}
	
	@Test
	public void testPriceReportDto2_1() {
		PriceReportDto prd = new PriceReportDto(null, 1.2, 1.1, 1.5, 0.8, 0.5, 1.4, null);
		Integer gsId = prd.getGasStationId();
		Assert.assertTrue(gsId == null);
	}

	@Test
	public void testPriceReportDto2_2() {
		PriceReportDto prd = new PriceReportDto(1, 1.2, 1.1, 1.5, 0.8, 0.5, 1.4, null);
		Integer gsId = prd.getGasStationId();
		Assert.assertTrue(gsId == 1);
	}

	@Test
	public void testPriceReportDto2_3() {
		PriceReportDto prd = new PriceReportDto(-1, 1.2, 1.1, 1.5, 0.8, 0.5, 1.4, null);
		Integer gsId = prd.getGasStationId();
		Assert.assertTrue(gsId == -1);
	}
	
	@Test
	public void testPriceReportDto3_1() {
		PriceReportDto prd = new PriceReportDto(0, 1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 0);
		Double dieselP = prd.getDieselPrice();
		Double superP = prd.getSuperPrice();
		Double superPlusP = prd.getSuperPlusPrice();
		Double gasP = prd.getGasPrice();
		Double methaneP = prd.getMethanePrice();
		Double premiumDieselP = prd.getPremiumDieselPrice();
		Assert.assertTrue(dieselP == 1.0);
		Assert.assertTrue(superP == 1.1);
		Assert.assertTrue(superPlusP == 1.2);
		Assert.assertTrue(gasP == 1.3);
		Assert.assertTrue(methaneP == 1.4);
		Assert.assertTrue(premiumDieselP == 1.5);		
	}
	
	@Test
	public void testPriceReportDto3_2() {
		PriceReportDto prd = new PriceReportDto(0, null, null, null, null, null, null, 0);
		Double dieselP = prd.getDieselPrice();
		Double superP = prd.getSuperPrice();
		Double superPlusP = prd.getSuperPlusPrice();
		Double gasP = prd.getGasPrice();
		Double methaneP = prd.getMethanePrice();
		Double premiumDieselP = prd.getPremiumDieselPrice();
		Assert.assertTrue(dieselP == null);
		Assert.assertTrue(superP == null);
		Assert.assertTrue(superPlusP == null);
		Assert.assertTrue(gasP == null);
		Assert.assertTrue(methaneP == null);
		Assert.assertTrue(premiumDieselP == null);		
	}
	
	@Test
	public void testPriceReportDto3_3() {
		PriceReportDto prd = new PriceReportDto(0, -1.0, -1.1, -1.2, -1.3, -1.4, -1.5, 0);
		Double dieselP = prd.getDieselPrice();
		Double superP = prd.getSuperPrice();
		Double superPlusP = prd.getSuperPlusPrice();
		Double gasP = prd.getGasPrice();
		Double methaneP = prd.getMethanePrice();
		Double premiumDieselP = prd.getPremiumDieselPrice();
		Assert.assertTrue(dieselP == -1.0);
		Assert.assertTrue(superP == -1.1);
		Assert.assertTrue(superPlusP == -1.2);
		Assert.assertTrue(gasP == -1.3);
		Assert.assertTrue(methaneP == -1.4);
		Assert.assertTrue(premiumDieselP == -1.5);		
	}
	
}
