package com.securityAuth.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.securityAuth.configurations.JwtUtil;
import com.securityAuth.model.AuthRequest;
import com.securityAuth.model.AuthResponse;
import com.securityAuth.service.UserDetailServiceImp;

@RestController
@CrossOrigin("*")
public class AuthController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailServiceImp userDetailsService;
	

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			//log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials");
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(jwt));

}
	
	
	@GetMapping("/validate")
	 protected boolean validateToken(@RequestHeader(value="Authorization") String token)
	            throws ServletException, IOException {
		
		return true;
	    }
	
	
}
