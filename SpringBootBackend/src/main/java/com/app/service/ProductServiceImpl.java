package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.entities.Category;
import com.app.entities.Product;
import com.app.entities.Seller;
import com.app.repository.ProductRepository;
import com.app.utils.StorageService;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired 
	ProductRepository productRepo;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired 
	SellerService sellerService;
	
	@Autowired
	CategoryService categoryService;
	
	@Override
	public Product addProduct(Product product, MultipartFile pic) {
		
		//pic is saved and returns the filepath in string
		String photo=storageService.store(pic);
		
		product.setPhoto(photo);
		productRepo.save(product);
		return product;
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		System.out.println("updating product.. ");
		Optional<Product> pp=productRepo.findById(product.getProductId());
		
		product.setSeller(pp.get().getSeller());
		productRepo.save(product);
	}

	@Override
	public List<Product> findProducts(int sellerId) {
		Optional<Seller> seller=sellerService.findSellerById(sellerId);
		return productRepo.findBySeller(seller.get());
	}

	@Override
	public List<Product> getAllProducts() {	
		return productRepo.findAll();
	}

	@Override
	public Optional<Product> findProductById(int productId) {	
		return productRepo.findById(productId);
	}

	@Override
	public List<Product> categoryProducts(String categoryName) {
		// TODO Auto-generated method stub
		Category category=categoryService.findByName(categoryName);
		 return productRepo.findByCategory(category);
	}

	@Override
	public Page<Product> allProductsPaginated(int page,int pagesize) {
		Page<Product> prods=productRepo.findAll(PageRequest.of(page, pagesize,Sort.by(Direction.DESC, "productId")));
		System.err.println(prods.getSize());
		return prods;
	}

}
