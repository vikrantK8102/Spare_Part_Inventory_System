package com.app.service;
/*
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.woodworks.project.entities.Customer;
import com.woodworks.project.repository.CustomerRepository;


@Service
public class JwtServiceImpl implements UserDetailsService {

	@Autowired
	CustomerRepository customerRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Customer customer = customerRepository.findByEmail(email);
		System.out.println(customer.getEmail()+" "+customer.getPassword());
		System.out.println();
		if (customer == null) {
			//throw new EmailNotFoundException("Customer not found with email: " + email);
		}
		return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(),
				new ArrayList<>());
	}

}
*/