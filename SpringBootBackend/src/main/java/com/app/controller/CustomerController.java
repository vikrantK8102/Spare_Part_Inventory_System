package com.app.controller;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Customer;
import com.app.entities.Feedback;
import com.app.models.CustomerDto;
import com.app.models.FeedbackDTO;
import com.app.models.LoginDTO;
import com.app.models.Response;
import com.app.service.CustomerService;
import com.app.service.EmailService;
import com.app.service.FeedbackService;
import com.app.service.OtpGenerator;



@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	OtpGenerator otpGenerator;
	
	@Autowired
	FeedbackService feedbackService;
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody CustomerDto customerDto) throws MessagingException{
		System.out.println("Registering Customer...");
		Customer cust=customerService.registerUser(customerDto);
		System.out.println(cust);
		if(cust!=null) {
			String str=cust.getEmail();
			//emailService.sendSimpleEmail(str,"You have registered successfully!\n Email : "+str,"Welcome To Woodworks.com services!!");
			return Response.success(cust);
		}else
			return Response.status(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/validate")
	public ResponseEntity<?> validateCustomer(@RequestBody LoginDTO dto) {
		System.out.println(dto);
		Customer customer=customerService.validate(dto.getEmail(),dto.getPassword());
		if(customer!=null)
			return Response.success(customer);
		else
			return Response.status(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllCustomers() {
		List<Customer> result = customerService.findAllCustomers();
		return Response.success(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable("id") int id) {
		System.out.println("findCustomerById "+id);
		Optional<Customer> result = customerService.findCustomerById(id);
		if(result!=null) {
		return Response.success(result.get());
		}else {
			return Response.error("Customer does not exist");
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateProfile(@RequestBody Customer cust,@PathVariable("id") int id) {
		customerService.updateProfile(cust,id);
		return Response.status(HttpStatus.OK);
	}
	
	@PostMapping("/forgetpassword")
	public ResponseEntity<?>forgetPassword(@RequestBody CustomerDto customerDto) throws MessagingException {	
		System.out.print("Sending OTP");
		String otp = otpGenerator.generateOTP();
		//emailService.sendOtp(customerDto.getEmail(),"OTP: "+otp,"OTP Verification! Woodworks.com! ");
		Customer cust =customerService.findByEmail(customerDto.getEmail());
		if(cust !=null) {
		CustomerDto custDto=new CustomerDto();
		BeanUtils.copyProperties(cust, custDto);
		custDto.setOtp(otp);
		System.out.print(otp);
		return Response.success(custDto);
		}else {
			return Response.status(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/resetpassword")
	public ResponseEntity<?>resetPassword(@RequestBody CustomerDto customerDto) throws MessagingException {	
		Customer cust =customerService.findByEmail(customerDto.getEmail());
		if(cust !=null) {
			customerService.resetPassword(cust,customerDto.getPassword());
			return Response.status(HttpStatus.OK);
		}else {
			return Response.status(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/feedback/{id}")
	public ResponseEntity<?> submitFeedback(@RequestBody FeedbackDTO dto,@PathVariable("id")int id) {
		System.out.println(dto);
		Optional<Customer> customer=customerService.findCustomerById(dto.getCustomerId());
		Feedback feedback=dto.toEntity(dto);
		feedback.setCustomer(customer.get());
		feedbackService.saveFeedback(feedback);
		System.out.println(feedback);
		return Response.success(feedback);
	}
	
	
	@GetMapping("/viewfeedback")
	public List<Feedback> findAllFeedback() {
		List<Feedback> result = feedbackService.findAllFeedbacks();
		return result;
	}
	
}
