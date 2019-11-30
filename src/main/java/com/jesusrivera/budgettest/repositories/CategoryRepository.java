package com.jesusrivera.budgettest.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jesusrivera.budgettest.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	public List<Category> findAll();
	
	public Optional<Category> findByName(String name);
	
}
