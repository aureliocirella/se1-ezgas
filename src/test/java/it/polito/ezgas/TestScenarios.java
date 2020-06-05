package it.polito.ezgas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.GasStationServiceimpl;
import it.polito.ezgas.service.impl.UserServiceimpl;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestScenarios {

	@Autowired 
	UserRepository userRepository;
	@Autowired
	UserConverter userConverter; 
	@Autowired 
	GasStationRepository gasStationRepository;
	@Autowired
	GasStationConverter gasStationConverter; 
	Integer GasStationId=0;
	@Before public void initialize() {
		try {
		 
			ArrayList<User> userslist= new ArrayList<User>();
			User user00= new User("Raquel"  , "Winters"  ,"Winters@ezgaz.com"  ,1 );
			User user01= new User("Layla"   , "Hancock"  ,"Hancock@ezgaz.com"  ,2 );
			User user02= new User("Van"     , "Rowland"  ,"Rowland@ezgaz.com"  ,4 );
			User user03= new User("Kamari"  , "Weiss"    ,"Weiss@ezgaz.com"    ,5 );
			User user04= new User("Sonny"   , "Shepherd" ,"Shepherd@ezgaz.com" ,3 );
			User user05= new User("Kristina", "Dorsey"   ,"Dorsey@ezgaz.com"   ,2 );
			User user06= new User("Everett" , "Paul"     ,"Paul@ezgaz.com"     ,2 );
			User user07= new User("Paisley" , "Buckley"  ,"Buckley@ezgaz.com"  ,4 );
			User user08= new User("Amari"   , "Goodwin"  ,"Goodwin@ezgaz.com"  ,5 );
			User user09= new User("Lisa"    , "Garrison" ,"Garrison@ezgaz.com" ,1 );
			User user10= new User("Amari"   , "Jenkins"  ,"Jenkins@ezgaz.com"  ,3 );
			User user11= new User("Jayson"  , "Anderson" ,"Anderson@ezgaz.com" ,1 );
		    User user12= new User("Pierre"  , "Cox" ,"Pierre@ezgaz.com" ,1 );
		    user12.setAdmin(true);
			 
			userslist.add(user00);
			userslist.add(user01);
			userslist.add(user02);
			userslist.add(user03);
			userslist.add(user04);
			userslist.add(user05);
			userslist.add(user06);
			userslist.add(user07);
			userslist.add(user08);
			userslist.add(user09);
			userslist.add(user10);
			userslist.add(user11);
			userslist.add(user12);
			
			userRepository.save(userslist);
			
			
			ArrayList<GasStation> gslist = new ArrayList<GasStation>();
			GasStation gasStation00 = new GasStation("Q8", "Corso Galileo Ferraris 36/A Turin Piemont Italy", false, false, false, false, false, "", 45.0627865, 7.6686337, -1.0, -1.0, -1.0, -1.0, -1.0, -1, "", -1.0);
			GasStation gasStation01 = new GasStation("Tamoil", "Marche Turin Piemont Italy ", true, true, false, false, false, "Enjoy", 45.0677551, 7.6824892, 1.5, 1.2, -1.0, -1.0, -1.0, user00.getUserId(), "05-28-2020", 1.2);
			GasStation gasStation02 = new GasStation("Eni Station", "Corso Giacomo Matteotti 12/Q Turin Piemont Italy", true, false, false, true, true, "Enjoy", 45.0303838, 7.6690677, -1.0, -1.0, -1.0, 1.5, 2.1, user00.getUserId(), "05-30-2020", 2.4);
			GasStation gasStation03 = new GasStation("Esso", "Via Francesco Cigna 40/B Turin Piemont Italy", true, false, true, false, false, "Car2Go", 45.0829594, 7.6792507, -1, -1, 1.78, -1, -1, user01.getUserId(), "05-20-2020", 1.0);
			GasStation gasStation04 = new GasStation("GPL Torino", "Corso Enrico Tazzoli 183/A Turin Piemont Italy", true, false, false, false, false, "Car2Go", 45.0355852, 7.6236845, -1, -1, -1, -1, -1, -1, "", -1.0);
		    
			gslist.add(gasStation00);
			gslist.add(gasStation01);
			gslist.add(gasStation02);
			gslist.add(gasStation03);
			gslist.add(gasStation04);
			
			gslist.forEach(gs -> {gasStationRepository.save(gs);});
			
	 
		}catch (Exception e) 
		{ 
			fail ("SetUp Error");
			}
    }
 
@After public void EndTest() {
	try {		
		GasStationServiceimpl gasStationImpl = new GasStationServiceimpl(gasStationRepository, userRepository,gasStationConverter);
		List<GasStationDto> gsd = gasStationImpl.getGasStationsByProximity(45.0627865, 7.6686337);
		if(gsd.size()>0)
		gasStationImpl.deleteGasStation(gsd.get(0).getGasStationId());
		gsd = gasStationImpl.getGasStationsByProximity(45.0677551, 7.6824892);
		if(gsd.size()>0)
		gasStationImpl.deleteGasStation(gsd.get(0).getGasStationId());
		gsd = gasStationImpl.getGasStationsByProximity(45.0303838, 7.6690677);
		if(gsd.size()>0)
		gasStationImpl.deleteGasStation(gsd.get(0).getGasStationId());
		gsd = gasStationImpl.getGasStationsByProximity(45.0829594, 7.6792507);
		if(gsd.size()>0)
		gasStationImpl.deleteGasStation(gsd.get(0).getGasStationId());
		gsd = gasStationImpl.getGasStationsByProximity(45.0355852, 7.6236845);
		if(gsd.size()>0)
		gasStationImpl.deleteGasStation(gsd.get(0).getGasStationId());    
		
			userRepository.delete( userRepository.findByEmail("Winters@ezgaz.com" ));
			userRepository.delete( userRepository.findByEmail("Hancock@ezgaz.com" )); 
			userRepository.delete( userRepository.findByEmail("Rowland@ezgaz.com" )); 
			userRepository.delete( userRepository.findByEmail("Weiss@ezgaz.com"   )); 
			userRepository.delete( userRepository.findByEmail("Shepherd@ezgaz.com")); 
			userRepository.delete( userRepository.findByEmail("Dorsey@ezgaz.com"  )); 
			userRepository.delete( userRepository.findByEmail("Paul@ezgaz.com"    )); 
			if(!userRepository.findByEmail("Buckley@ezgaz.com").isEmpty())
			userRepository.delete( userRepository.findByEmail("Buckley@ezgaz.com" )); 
			userRepository.delete( userRepository.findByEmail("Goodwin@ezgaz.com" )); 
			userRepository.delete( userRepository.findByEmail("Garrison@ezgaz.com")); 
			userRepository.delete( userRepository.findByEmail("Jenkins@ezgaz.com" )); 
			userRepository.delete( userRepository.findByEmail("Anderson@ezgaz.com")); 
			
			if(!userRepository.findByEmail("Gardner@ezgaz.it").isEmpty())
				userRepository.delete( userRepository.findByEmail("Gardner@ezgaz.it"));
			if(!userRepository.findByEmail("Pierre@ezgaz.com").isEmpty())
				userRepository.delete( userRepository.findByEmail("Pierre@ezgaz.com"));
		
			
			
			
			
		}catch (Exception e) 
		{ 
			fail ("EndTest Error");
		}
    }
	@Test
	public void testScenario1() {
		try {
			 
			UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
			 
			GasStationServiceimpl gasStationImpl = new GasStationServiceimpl(gasStationRepository, userRepository,gasStationConverter);
		 
			LoginDto userdto=	userImpl.login(new IdPw("Pierre@ezgaz.com","Cox"));
			 
			ArrayList<User> log = userRepository.findByEmail(userdto.getEmail());
			List<GasStationDto> gsdl = gasStationImpl.getGasStationsWithCoordinates(45.0677551, 7.6824892, "Diesel", "Enjoy");
			if(log.size()==1)
				{
				
				 User foundUser= log.get(0);
				 if(gsdl==null)
					 fail("gas station list is null");
				 if(gsdl.size() == 0)
					 fail("No gas station found");
				 GasStationDto gsd = gsdl.get(0);
				System.out.println(gsd.getGasStationId()+", "+gsd.getDieselPrice()+","+gsd.getSuperPrice()+","+gsd.getSuperPlusPrice()+","+gsd.getGasPrice());
				gasStationImpl.setReport(gsd.getGasStationId(), 1.0, -1.0, -1.0, 2.1, -1.0, foundUser.getUserId());
				
				GasStation gs = gasStationRepository.findOne(gsd.getGasStationId());
				System.out.println(gs.getDieselPrice()+","+gs.getSuperPrice()+","+gs.getSuperPlusPrice()+","+gs.getGasPrice());

				Assert.assertEquals(gs.getDieselPrice(), 1.0, 0);
				Assert.assertTrue(gs.getSuperPrice()==-1.0);
				Assert.assertTrue(gs.getSuperPlusPrice()==-1.0);
				Assert.assertTrue(gs.getGasPrice()==2.1);
				Assert.assertTrue(gs.getMethanePrice()==-1.0);
				}
			 
		} catch (Exception e) {
			fail("Exception throwed");
			}
		
	}
	
}
