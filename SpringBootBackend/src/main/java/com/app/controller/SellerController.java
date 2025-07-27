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
import com.app.entities.Seller;
import com.app.models.CustomerDto;
import com.app.models.LoginDTO;
import com.app.models.Response;
import com.app.models.SellerDto;
import com.app.service.EmailService;
import com.app.service.OtpGenerator;
import com.app.service.ProductService;
import com.app.service.SellerService;


@CrossOrigin
@RestController
@RequestMapping("/api/sellers")
public class SellerController {

	@Autowired
	SellerService sellerService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired 
	private ProductService productService;
	
	
	@Autowired
	OtpGenerator otpGenerator;
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody SellerDto sellerDto) throws MessagingException{
		Seller seller=sellerService.registerSeller(sellerDto);
		if(seller!=null) {
			String str=seller.getEmail();
			emailService.sendSimpleEmail(str,"You have registered successfully!\n Email : "+str+"\n Password = "+seller.getPassword(),"Welcome To Woodworks.com services!!");
			return Response.success(seller);
		}else
			return Response.status(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/validate")
	public ResponseEntity<?> validateUser(@RequestBody LoginDTO dto) {
		System.out.println(dto);
		Seller seller=sellerService.validate(dto.getEmail(),dto.getPassword());
		if(seller!=null)
			return Response.success(seller);
		else
			return Response.status(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllSellers() {
		System.out.println("findAllSellers...");
		List<Seller> result = sellerService.findAllSellers();
		return Response.success(result);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findSellerById(@PathVariable("id") int id) {
		System.out.println("findSellerById...");
		Optional<Seller> result = sellerService.findSellerById(id);
		return Response.success(result.get());
	}
	
	/* we have to delete seller but for that 1st we have to delete product but for 
	 * deleting product we have to delete orderdetails and we cannot loose the data or order 
	 * so kahi garaj nahi delete karaychi 
	 
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteSeller(@PathVariable("id") int id) {
		int sellerId=id;
		List<Product>products=productService.findProducts(sellerId);
		for(Product product:products) {
			productService.deleteProduct(product.getProdid());
		}
		sellerService.deleteSeller(id);
		return Response.status(HttpStatus.OK);
	}
	*/
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateProfile(@RequestBody Seller seller,@PathVariable("id") int id) {
		sellerService.updateProfile(seller,id);
		return Response.status(HttpStatus.OK);
	}
	
	@PostMapping("/forgetpassword")
	public ResponseEntity<?>forgetPassword(@RequestBody SellerDto sellerDto) throws MessagingException {	
		System.out.print("Sending OTP");
		String otp = otpGenerator.generateOTP();
		emailService.sendOtp(sellerDto.getEmail(),"OTP: "+otp,"OTP Verification! Woodworks.com! ");
		Seller seller =sellerService.findByEmail(sellerDto.getEmail());
		if(seller !=null) {
		SellerDto sellDto=new SellerDto();
		BeanUtils.copyProperties(seller, sellDto);
		sellDto.setOtp(otp);
		System.out.print(otp);
		return Response.success(sellDto);
		}else {
			return Response.status(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/resetpassword")
	public ResponseEntity<?>resetPassword(@RequestBody SellerDto sellerDto) throws MessagingException {	
		Seller seller =sellerService.findByEmail(sellerDto.getEmail());
		if(seller !=null) {
			sellerService.resetPassword(seller,sellerDto.getPassword());
			return Response.status(HttpStatus.OK);
		}else {
			return Response.status(HttpStatus.NOT_FOUND);
		}
	}
}
