package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Category;
import com.app.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository catRepo;



	@Override
	public Category findByName(String categoryName) {
		return catRepo.findByCategoryName(categoryName);
	}


	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return catRepo.save(category);
	}


	@Override
	public List<Category> findAllCategory() {
		// TODO Auto-generated method stub
		return catRepo.findAll();
	}

}
