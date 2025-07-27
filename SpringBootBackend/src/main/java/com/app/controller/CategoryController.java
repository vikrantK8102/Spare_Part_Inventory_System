package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Category;
import com.app.models.Response;
import com.app.service.CategoryService;

@RestController
@CrossOrigin()
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/addcategory")
	public ResponseEntity<?> addCategory(@RequestBody Category category){
		
		Category cat=categoryService.findByName(category.getCategoryName());
		if(cat!=null)
			return Response.status(HttpStatus.NOT_FOUND);
		else {
				Category categ= categoryService.addCategory(category);
				return Response.success(categ);
			}
		}
	
	@GetMapping("/getcategory/{categoryName}")
	public Category getCategoryById(@PathVariable("categoryName")String categoryName) {
	 
		return categoryService.findByName(categoryName);
	}
	
	@GetMapping("/getallcategory")
	public List<Category> getAllCategory() {
		return categoryService.findAllCategory();
	}
	

}
