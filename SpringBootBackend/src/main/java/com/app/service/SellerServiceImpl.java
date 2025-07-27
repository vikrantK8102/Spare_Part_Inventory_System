package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entities.Customer;
import com.app.entities.Seller;
import com.app.models.SellerDto;
import com.app.repository.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService{
	
	@Autowired
	SellerRepository sellerRepo;
	
	PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

	@Override
	public Seller registerSeller(SellerDto sellerDto) {
		Seller seller=sellerRepo.findByEmail(sellerDto.getEmail());
		
		if(seller!=null) {
			return null;
		}else {
			Seller seller1 = new Seller();
			String encodedPassword = passwordEncoder.encode(sellerDto.getPassword());
			sellerDto.setPassword(encodedPassword);
			BeanUtils.copyProperties(sellerDto, seller1);
			System.out.println(seller1);
			return sellerRepo.save(seller1);
		}
	}

	@Override
	public Seller validate(String email, String password) {
		Seller seller=sellerRepo.findByEmail(email);
		if(seller!=null && passwordEncoder.matches(password, seller.getPassword())) {
			return seller;
		}
		return null;
	}

	@Override
	public List<Seller> findAllSellers() {
		// TODO Auto-generated method stub
		return sellerRepo.findAll();
	}

	@Override
	public Optional<Seller> findSellerById(int id) {
		// TODO Auto-generated method stub
		return sellerRepo.findById(id);
	}

	@Override
	public void updateProfile(Seller seller, int id) {
		// TODO Auto-generated method stub
		System.out.println("Updating profile..."+id);
		Optional<Seller> seller1=sellerRepo.findById(id);
		if(seller1!=null) {
			if(seller.getPassword().equals("") || seller.getPassword()==null) {
				
				seller.setPassword(seller1.get().getPassword());
			}else if(seller.getPassword().equals(seller1.get().getPassword())) {
				seller.setPassword(seller1.get().getPassword());
			}else {
				String encodedPassword = passwordEncoder.encode(seller.getPassword());
				seller.setPassword(encodedPassword);
			}
			sellerRepo.save(seller);
		}
	}

	@Override
	public Seller findByEmail(String email) {
		Seller seller=sellerRepo.findByEmail(email);
		return seller;
	}

	@Override
	public void resetPassword(Seller seller, String password) {
		String encodedPassword = passwordEncoder.encode(password);
		seller.setPassword(encodedPassword);
		sellerRepo.save(seller);
	}

}
