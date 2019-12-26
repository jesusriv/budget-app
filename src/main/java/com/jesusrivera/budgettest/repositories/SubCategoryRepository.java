package com.jesusrivera.budgettest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jesusrivera.budgettest.models.Budget;
import com.jesusrivera.budgettest.models.SubCategory;

public interface SubCategoryRepository extends CrudRepository<SubCategory, Long> {
	public List<SubCategory> findAll();
	public List<SubCategory> findByBudget(Budget budget);
}
