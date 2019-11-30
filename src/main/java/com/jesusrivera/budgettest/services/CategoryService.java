package com.jesusrivera.budgettest.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesusrivera.budgettest.models.Category;
import com.jesusrivera.budgettest.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired 
	private CategoryRepository cR;
	
	public Category create(String name) {
		Category category = new Category();
		return cR.save(category);
	}
	
	public Category createNew(Category category) {
		return cR.save(category);
	}
	
	public Category findById(Long id) {
		Optional<Category> oC = cR.findById(id);
		if (oC.isPresent()) {
			Category c = oC.get();
			return c;
		}
		return null;
	}
	
	public List<Category> findAll() {
		return cR.findAll();
	}
	
	public Category findByName(String name) {
		Optional<Category> oC = cR.findByName(name);
		if (oC.isPresent()) {
			Category c = oC.get();
			return c;
		}
		return null;
	}
	
}
