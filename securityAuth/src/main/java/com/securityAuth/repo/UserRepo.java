package com.securityAuth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securityAuth.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
	public User findByUsername(String username);
	

}
