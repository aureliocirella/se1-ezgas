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
				GasStation gasStation01 = new GasStation("Tamoil", "Marche Turin Piemont Italy ", true, true, false, false, false, "Enjoy", 45.0677551, 7.6824892, 1.5, 1.2, -1.0, -1.0, -1.0, user00.getUserId(), "2020.05.10.13.05.01", 1.2);
				GasStation gasStation02 = new GasStation("Eni Station", "Corso Giacomo Matteotti 12/Q Turin Piemont Italy", false, false, false, true, true, "Enjoy", 45.0303838, 7.6690677, -1.0, -1.0, -1.0, 1.5, 2.1, user00.getUserId(), "2020.05.15.12.45.45", 2.4);
				GasStation gasStation03 = new GasStation("Esso", "Via Francesco Cigna 40/B Turin Piemont Italy", false, false, true, false, false, "Car2Go", 45.0829594, 7.6792507, -1, -1, 1.78, -1, -1, user01.getUserId(), "2020.05.08.23.33.28", 1.0);
				GasStation gasStation04 = new GasStation("GPL Torino", "Corso Enrico Tazzoli 183/A Turin Piemont Italy", false, false, false, false, false, "Car2Go", 45.0355852, 7.6236845, -1, -1, -1, -1, -1, -1, "", -1.0);
				
				gslist.add(gasStation00);
				gslist.add(gasStation01);
				gslist.add(gasStation02);
				gslist.add(gasStation03);
				gslist.add(gasStation04);
				
				gasStationRepository.save(gslist);
				
		 
			}catch (Exception e) 
			{ 
				fail ("SetUp Error");
				}
	    }
	 
	@After public void EndTest() {
		try {		
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
		
			
			GasStationServiceimpl gasStationImpl = new GasStationServiceimpl(gasStationRepository, userRepository,gasStationConverter);
			List<GasStationDto> gsd = gasStationImpl.getGasStationsByProximity(45.0627865, 7.6686337);
			gasStationImpl.deleteGasStation(gsd.get(0).getGasStationId());
			gsd = gasStationImpl.getGasStationsByProximity(45.0627865, 7.6686337);
			gasStationImpl.deleteGasStation(gsd.get(0).getGasStationId());
			gsd = gasStationImpl.getGasStationsByProximity(45.0303838, 7.6690677);
			gasStationImpl.deleteGasStation(gsd.get(0).getGasStationId());
			gsd = gasStationImpl.getGasStationsByProximity(45.0829594, 7.6792507);
			gasStationImpl.deleteGasStation(gsd.get(0).getGasStationId());
			gsd = gasStationImpl.getGasStationsByProximity(45.0355852, 7.6236845);
			gasStationImpl.deleteGasStation(gsd.get(0).getGasStationId());
			
			
		}catch (Exception e) 
		{ 
			fail ("EndTest Error");
		}
    }
	@Test
	public void testScenario1() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
			UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
			UserDto user = new UserDto(1, "Gardner", "Kelvin", "Gardner@ezgaz.it", 1);
			userImpl.saveUser(user); 
			
			
		   LoginDto userdto= userImpl.login(new IdPw("Gardner@ezgaz.it","Kelvin"));
		    ArrayList<User> log = userRepository.findByEmail(userdto.getEmail());
		    
			if(log.size()==1)
				{
				
				 User foundUser= log.get(0);	 
				 assertEquals("Gardner",foundUser.getUserName());
				}
			    else if(log.size()>0)
				{
				 
				 fail("More than one user found with this email");			
				}
			     else if(log.size()==0)
				{ 
				 fail("No user found with this email");		
				}
			conn.close(); 
		} catch (Exception e) {
			fail("Exception throwed");
			}
		
	}
	@Test
	public void testScenario2() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
			UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		LoginDto userdto=	userImpl.login(new IdPw("Shepherd@ezgaz.com","Shepherd"));
		    ArrayList<User> log = userRepository.findByEmail(userdto.getEmail());
		 
			if(log.size()==1)
				{
				
				 User foundUser= log.get(0);
		 
				 Integer expectedReputation=3;
				 assertEquals(expectedReputation,foundUser.getReputation());
				 assertEquals("Sonny",foundUser.getUserName());
				 Integer userRep= foundUser.getReputation();
				 userRep++;
				 Integer NewRep=userImpl.increaseUserReputation(foundUser.getUserId());
				assertEquals(userRep,NewRep ); 
				
				 
				}
			    else if(log.size()>0)
				{
				 
				 fail("More than one user found with this email");			
				}
			     else if(log.size()==0)
				{
				 
				 fail("No user found with this email");		
				}
			conn.close(); 
		} catch (Exception e) {
			fail("Exception throwed");
			}
		
	}
	@Test
	public void testScenario3() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
			UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
		    LoginDto userdto=	userImpl.login(new IdPw("Pierre@ezgaz.com","Cox"));
		    ArrayList<User> log = userRepository.findByEmail(userdto.getEmail());
		 
			if(log.size()==1)
				{
				
				 User foundUser= log.get(0);
				 if(foundUser.getAdmin())
				 {
					    ArrayList<User> userclient = userRepository.findByEmail("Buckley@ezgaz.com");
					    userImpl.deleteUser(userclient.get(0).getUserId());
				 }
				 else
					 
					 fail("User is not admin to delete the user");
				 
				 
				}
			    else if(log.size()>0)
				{
				 
				 fail("More than one user found with this email");			
				}
			     else if(log.size()==0)
				{
				 
				 fail("No user found with this email");		
				}
			conn.close(); 
		} catch (Exception e) {
			fail("Exception throwed");
			}
		
	}

	@Test
	public void testScenario4() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
			UserServiceimpl userImpl = new UserServiceimpl(userRepository,userConverter);
			GasStationServiceimpl gasStationImpl = new GasStationServiceimpl(gasStationRepository, userRepository,gasStationConverter);
			LoginDto userdto=	userImpl.login(new IdPw("Pierre@ezgaz.com","Cox"));
		    ArrayList<User> log = userRepository.findByEmail(userdto.getEmail());
			List<GasStationDto> gsd=	gasStationImpl.getGasStationsWithCoordinates(45.0627865, 7.6686337, null, null);
		 
			if(log.size()==1)
				{
				
				 User foundUser= log.get(0);

				 if(gsd.size() == 0)
					 fail("No gas station found");
				 else if (gsd.size() > 1)
					fail("More than one gas station found");
				 
				gasStationImpl.setReport(gsd.get(0).getGasStationId(), 1.0, -1, -1, 2.1, -1, foundUser.getUserId());
				
				}
				 
			conn.close(); 
		} catch (Exception e) {
			fail("Exception throwed");
			}
		
	}
	
	
	
}
