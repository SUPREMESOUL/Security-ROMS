package com.securityAuth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@NoArgsConstructor
@Table(name="users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @Column(name="Name",nullable = false, unique = true)
    private String username;
    @Column(name="MyPassword",nullable = false)
    private String password;
    
    

    public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

    // standard getters and setters
}
