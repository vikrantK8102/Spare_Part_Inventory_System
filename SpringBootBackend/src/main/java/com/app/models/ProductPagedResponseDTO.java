package com.app.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPagedResponseDTO {
	
	private List<ProductResponseDto> plist;
	
	private int current;
	
	private long total;
	
	private int pagesize;
	
	public List<ProductResponseDto> getPlist() {
		return plist;
	}
}
