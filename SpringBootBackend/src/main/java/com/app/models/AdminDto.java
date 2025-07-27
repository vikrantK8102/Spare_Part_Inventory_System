package com.app.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
	
	private int id;
	
	private String email;
	
	private String password;
	
	private String uname;
	
	//used for forget password
	private String otp;
}
