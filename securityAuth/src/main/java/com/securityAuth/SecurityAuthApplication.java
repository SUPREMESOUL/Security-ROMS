package com.securityAuth;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.securityAuth.model.User;
import com.securityAuth.repo.UserRepo;

@SpringBootApplication
public class SecurityAuthApplication {
	
	@Autowired
  public UserRepo repo;
	@Autowired
	public 	 PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SecurityAuthApplication.class, args);
	}

	
	
@PostConstruct
	
	public void initUsers() {
	
		List<User> users =Stream.of(new User("pranja123@gmail.com", passwordEncoder.encode("pranjal@123")),
				new User("kiran", passwordEncoder.encode("kiran@123")),
				new User("rahul", passwordEncoder.encode("rahul@123"))).collect(Collectors.toList());
		repo.saveAll(users);
		
	}
}
