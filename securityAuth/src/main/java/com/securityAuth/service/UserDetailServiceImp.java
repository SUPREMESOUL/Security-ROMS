package com.securityAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.securityAuth.model.MyUserPrincipal;
import com.securityAuth.repo.UserRepo;
import com.securityAuth.model.User;

	

@Service
public class UserDetailServiceImp implements UserDetailsService {
@Autowired
private UserRepo userrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userrepo.findByUsername(username);
	        if (user == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return new MyUserPrincipal(user);
	}

}

