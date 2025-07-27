package com.app.service;

import com.app.entities.Payment;

public interface PaymentService {

	Payment savePayment(Payment payment);
	
	Payment findPaymentById(int id);
}
