package com.jesusrivera.budgettest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jesusrivera.budgettest.models.Budget;
import com.jesusrivera.budgettest.models.User;

public interface BudgetRepository extends CrudRepository<Budget, Long>{

	public List<Budget> findAll();
	public List<Budget> findByUser(User user);
}
