package com.app.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {

	private int productId;
	
	private String categoryName;
	
	private String pname;
	
	private int price;
	
	private int qty;
	
	@Override
	public String toString() {
		return "CartDTO [productId=" + productId + ", categoryName=" + categoryName + ", pname=" + pname + ", price=" + price + ", qty=" + qty
				+ "]";
	}
}
