package com.app.models;

import java.util.List;

import com.app.entities.Orders;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDTO {

	private Orders order;
	
	private List<OrderDetailsDTO> details;
	
}
