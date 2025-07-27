package com.app.models;
import java.util.List;

import com.app.entities.Address;
import com.app.entities.Payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderDTO {


	private Address address;
	
	private List<CartDTO> cart;
	
	private Payment payment;
	
	private int customerId;
	
//	public int getCustomerid() {
//		return customerid;
//	}
//	public void setCustomerid(int customerid) {
//		this.customerid = customerid;
//	}
//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}
//	public List<CartDTO> getCart() {
//		return cart;
//	}
//	
//	public void setCart(List<CartDTO> cart) {
//		this.cart = cart;
//	}
//	public Payment getPayment() {
//		return payment;
//	}
//	public void setPayment(Payment payment) {
//		this.payment = payment;
//	}
}
