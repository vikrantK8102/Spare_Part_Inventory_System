package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entities.Customer;
import com.app.models.CustomerDto;
import com.app.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepo;
	
	PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	@Override
	public Customer registerUser(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		Customer cust=customerRepo.findByEmail(customerDto.getEmail());
		System.out.println(cust);
		if(cust!=null) {
				return null;
		}else {
			Customer customer = new Customer();
			String encodedPassword = passwordEncoder.encode(customerDto.getPassword());
			customerDto.setPassword(encodedPassword);
			BeanUtils.copyProperties(customerDto, customer);
			System.out.println(customer);
			return customerRepo.save(customer);
		}
	}

	@Override
	public Customer validate(String email, String password) {
		System.out.println("validating...");
		Customer customer=customerRepo.findByEmail(email);
		System.out.println(customer.getPassword());
		
		if(customer!=null && passwordEncoder.matches(password, customer.getPassword())) {
			return customer;
		}
		return null;
	}

	@Override
	public List<Customer> findAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Optional<Customer> findCustomerById(int id) {
		Optional<Customer> customer = customerRepo.findById(id);
		System.out.println(customer);
		if(customer!=null) {
		return customer;
		}else {
			return null;
		}
	}

	@Override
	public void updateProfile(Customer cust,int id) {
		System.out.println("Updating profile..."+id);
		// TODO Auto-generated method stub
		Optional<Customer> cust1=customerRepo.findById(id);
		if(cust1!=null) {
			if(cust.getPassword().equals("") || cust.getPassword()==null) {
				
				cust.setPassword(cust1.get().getPassword());
			}else if(cust.getPassword().equals(cust1.get().getPassword())) {
				cust.setPassword(cust1.get().getPassword());
			}else {
				String encodedPassword = passwordEncoder.encode(cust.getPassword());
				cust.setPassword(encodedPassword);
			}
			
			customerRepo.save(cust);
		}
	}

	@Override
	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		Customer customer=customerRepo.findByEmail(email);
		return customer;
	}

	@Override
	public void resetPassword(Customer cust, String password) {
		String encodedPassword = passwordEncoder.encode(password);
		cust.setPassword(encodedPassword);
		customerRepo.save(cust);
	}
}
