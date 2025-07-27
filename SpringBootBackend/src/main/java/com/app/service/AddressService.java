package com.app.service;

import com.app.entities.Address;

public interface AddressService {
	
	Address saveAddress(Address address);
	
	Address findAddress(int id);
}
