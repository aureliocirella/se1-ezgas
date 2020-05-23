package it.polito.ezgas.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
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
		for (Iterator<User> userIt = userRepository.findAll().iterator(); userIt.hasNext(); ) {
			User user =  userIt.next();
			if(user.getEmail().equals(credentials.getUser()) && user.getPassword().equals(credentials.getPw()) ) 
				  return userConverter.map(user, LoginDto.class);		 
		 }
		throw new InvalidLoginDataException("Login failed for " + credentials.getUser());
	}

	private Integer changeUserReputation(Integer userId, Integer var) throws InvalidUserException {
		if(userRepository.exists(userId)) {
			User user = userRepository.findOne(userId);
			UserDto userDto = userConverter.map(user, UserDto.class);
			int reputation = userDto.getReputation() + var;
			userDto.setReputation(reputation);
			userRepository.delete(userId);
			userRepository.save(userConverter.map(userDto, User.class));
			return reputation;
		}
		else {
			throw new InvalidUserException("Selected user does not exist!");
		}
	}
	
	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		return changeUserReputation(userId, 1);
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		return changeUserReputation(userId, -1);
	}
	
}
