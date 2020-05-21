package it.polito.ezgas.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import it.polito.ezgas.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

		ArrayList<User> findByAdmin(Boolean admin); 
	
	

}
