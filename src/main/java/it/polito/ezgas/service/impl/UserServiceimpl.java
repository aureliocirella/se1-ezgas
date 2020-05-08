package it.polito.ezgas.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
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
	
	
	ModelMapper modelMapper = new ModelMapper();
	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		
		User us = userRepository.findOne(userId); 
		return modelMapper.map(us, UserDto.class);
		
		
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		
		userRepository.save(modelMapper.map(userDto, User.class)); 
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteUser(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		//System.out.println(credentials.getUser() + ", " + credentials.getPw());
		for (Iterator<User> userIt = userRepository.findAll().iterator(); userIt.hasNext(); ) {
			User user =  userIt.next();
			//System.out.println("* " + user.getEmail() + ", " + user.getPassword());
			if(user.getEmail().equals(credentials.getUser()) && user.getPassword().equals(credentials.getPw()) ) {
				  //System.out.println("Found");
				  return modelMapper.map(user, LoginDto.class);		 
			 }
		 }
		return null;
	}

	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
