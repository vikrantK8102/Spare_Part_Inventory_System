package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entities.OrderDetails;
import com.app.entities.Orders;


public interface OrderDetailsService {

	void saveOrderDetails(OrderDetails od);
	OrderDetails findById(int id);
	List<OrderDetails> findByOrder(Orders order);
	
}
