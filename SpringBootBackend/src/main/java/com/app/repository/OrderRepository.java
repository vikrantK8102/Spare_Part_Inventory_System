package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Customer;
import com.app.entities.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{
	
	List<Orders> findByCustomer(Customer customer);
	
}
