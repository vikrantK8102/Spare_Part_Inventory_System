package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entities.Customer;
import com.app.models.CustomerDto;

public interface CustomerService {

	Customer registerUser(CustomerDto customerDto);

	Customer validate(String email, String password);

	List<Customer> findAllCustomers();

	Optional<Customer> findCustomerById(int id) ;

	void updateProfile(Customer cust, int id);

	Customer findByEmail(String email);

	void resetPassword(Customer cust, String password);

}
