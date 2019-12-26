package com.jesusrivera.budgettest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesusrivera.budgettest.models.Budget;
import com.jesusrivera.budgettest.services.BudgetService;

@RestController
public class BudgetController {

	@Autowired
	public BudgetService bS;
	
//	public Budget createForRegistration() {
//		Budget budget = bS.createForRegistration();
//		return budget;
//	}
//	
	@GetMapping("/api/budgets")
	public List<Budget> getAlllBudgets() {
		return bS.getAllBudgets();
	}
	
	@GetMapping("/api/budget/{id}")
	public Budget getById(@PathVariable("id") Long id) {
		Budget b = bS.findById(id);
		return bS.findById(id);
	}
	
	@PutMapping("/api/budget/update/{id}")
	public Budget updateBudget(@PathVariable("id") Long id, Budget budget) {
		return bS.updateBudget(id, budget);
	}
	
	@DeleteMapping("/api/budget/delete/{id}")
	public String deleteBudget(@PathVariable("id") Long id) {
		String result = bS.delete(id);
		if (result == "success") {
			return "Successfully deleted";
		}
		return "Something went wrong";
	}
	
}
