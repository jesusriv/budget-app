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
	
	// GET ALL USER BUDGETS
	@GetMapping("/api/budgets/{userId}")
	public List<Budget> getAlllBudgets(@PathVariable("userId") Long id) {
		return bS.getAllBudgetsByUser(id);
	}
	
	// GET BUDGET BY ID
	@GetMapping("/api/budget/{id}")
	public Budget getById(@PathVariable("id") Long id) {
		return bS.findById(id);
	}
	
	// UPDATE BUDGET
	@PutMapping("/api/budget/update/{id}")
	public Budget updateBudget(@PathVariable("id") Long id, Budget budget) {
		return bS.updateBudget(id, budget);
	}
	
	// DELETE BUDGET 
	@DeleteMapping("/api/budget/delete/{id}")
	public String deleteBudget(@PathVariable("id") Long id) {
		String result = bS.delete(id);
		if (result == "success") {
			return "Successfully deleted";
		}
		return "Something went wrong";
	}
	
}
