package com.app.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerDto {

	private String name;
	
	private String city;
	
	private String email;
	
	private String password;
	
	//@Length(max=10,min=10)
	private long phone;
	
	private Date createdTimestamp=new Date();
	
	//used for forget password
	private String otp;
}
