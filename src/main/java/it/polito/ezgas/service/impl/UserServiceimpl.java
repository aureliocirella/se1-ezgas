package it.polito.ezgas.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class UserServiceimpl implements UserService {

	@Autowired 
	UserRepository userRepository;
	@Autowired 
	GasStationRepository gasStationRepository; 
	

	@Autowired
	UserConverter userConverter; 
	
	  public UserServiceimpl(UserRepository userRepositoryinput, UserConverter userConverterinput) {
		  userRepository=userRepositoryinput;
		  userConverter=userConverterinput;
	    }
	
	
	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		
		if(userId<0)
		{
			throw new InvalidUserException("Invalid userId!"); 
		}
		if(!userRepository.exists(userId))
		{
			return null; 
		}
		User us = userRepository.findOne(userId); 
		return userConverter.map(us, UserDto.class);
		
		
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		 User usernew =userConverter.map(userDto, User.class);
		 usernew.setReputation(0);
		 User user = userRepository.save(usernew); 
		return   userConverter.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// get users list and convert it to userDTO list
		return ((List<User>) userRepository.findAll())
		        .stream()
		        .map(source-> userConverter.map(source,UserDto.class))
		        .collect(Collectors.toList());
	}

	@Override
	public Boolean deleteUser(Integer userId) throws InvalidUserException {
		 // In the case of negative id throw exception
		if(userId<0)
		{
			throw new InvalidUserException("Invalid userId!"); 
		}
		 // Check if exists in db
		if(!userRepository.exists(userId))
		{
			return null; 
		}
		 userRepository.delete(userId);	
		  return true;
		 
		
	}

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		ArrayList<User> users= userRepository.findByEmail(credentials.getUser());
		for (Iterator<User> userIt = users.iterator(); userIt.hasNext(); ) {
			User user =  userIt.next();
			if(user.getEmail().equals(credentials.getUser()) && user.getPassword().equals(credentials.getPw()) ) 
				  return userConverter.map(user, LoginDto.class);		 
		 }
		throw new InvalidLoginDataException("Login failed for " + credentials.getUser());
	}

	private Integer changeUserReputation(Integer userId, Integer var) throws InvalidUserException {
		if(userRepository.exists(userId)) {
			User user = userRepository.findOne(userId);
		    int reputation = user.getReputation() + var;
		    if(reputation > 5)
		    {
		    	reputation = 5; 
		    }else if(reputation < -5)
		    {
		    	reputation = -5;
		    }
		   
			user.setReputation(reputation);
			return reputation;
		}
		else {
			throw new InvalidUserException("Selected user does not exist!");
		}
	}
	
	
	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		
		Integer newReputation = changeUserReputation(userId, 1);
		 
		List<GasStation> gsList = gasStationRepository.findByReportUser(userId);
		gsList.forEach((gs)-> { 
			Date today = new Date();
			DateFormat formatter = new SimpleDateFormat("MM-dd-YYYY");
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.ENGLISH);
			Date timestamp;
			try {
				timestamp = formatter.parse(gs.getReportTimestamp());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			
			long diffInMillies = Math.abs(timestamp.getTime() - today.getTime());
			long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			
			Integer obs = (int) ((diff > 7) ? 0:(1-(diff/7)));
			//System.out.println(50 * (newReputation+5) / 10 + 50*obs);
			
			gs.setReportDependability(50 * (newReputation+5) / 10 + 50*obs);
		});
		return newReputation;
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		Integer newReputation = changeUserReputation(userId, -1);
		if(newReputation<0) return newReputation;
		
		List<GasStation> gsList = gasStationRepository.findByReportUser(userId);
		gsList.forEach((gs)-> { 
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.ENGLISH);
			Date timestamp;
			try {
				timestamp = sdf.parse(gs.getReportTimestamp());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			//System.out.println(gs.getGasStationId());
			long diffInMillies = Math.abs(timestamp.getTime() - today.getTime());
			long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			
			Integer obs = (int) ((diff > 7) ? 0:(1-(diff/7)));

			gs.setReportDependability(50 * (newReputation+5) / 10 + 50*obs);
		});
		return newReputation;
	}
	
}
