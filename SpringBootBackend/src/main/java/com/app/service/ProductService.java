package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.app.entities.Product;

public interface ProductService {

	Product addProduct(Product product, MultipartFile pic);

	void updateProduct(Product product);

	List<Product> findProducts(int sellerId);

	List<Product> getAllProducts();

	Optional<Product> findProductById(int id);

	List<Product> categoryProducts(String pcategory);

	Page<Product> allProductsPaginated(int page, int pagesize);

}
