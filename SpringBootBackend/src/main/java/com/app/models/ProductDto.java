package com.app.models;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.app.entities.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

	private int productId;
	
	private String pname;
	
	private String categoryName;
	
	private double price;
	
	private int sellerId;
	
	private String brand;
	
	//used while updating the product
	private String photo;
	
	//used while uploading the product
	private MultipartFile pic;
	
	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", pname=" + pname + ", categoryName=" + categoryName + ",price=" + price + ", sellerId=" + sellerId + "]";
	}
	
	public static Product toEntity(ProductDto dto) {
		Product product=new Product();
		// source to destination copied excluding pic parameter
		BeanUtils.copyProperties(dto, product, "pic");		
		return product;
	}
}
