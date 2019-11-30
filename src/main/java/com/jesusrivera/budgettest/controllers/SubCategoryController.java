package com.jesusrivera.budgettest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jesusrivera.budgettest.models.Category;
import com.jesusrivera.budgettest.models.SubCategory;
import com.jesusrivera.budgettest.services.CategoryService;
import com.jesusrivera.budgettest.services.SubCategoryService;

@Controller
public class SubCategoryController {

	@Autowired
	private SubCategoryService sS;
	@Autowired
	private CategoryService cS;
	
	// Create
	@PostMapping("/api/subcategory/create/{id}")
	public SubCategory create(@PathVariable("id") Long categoryId, String name) {
		Category c = cS.findById(categoryId);
		List<SubCategory> subList = c.getSubcategory();
		SubCategory s =  sS.create(name);
		subList.add(s);
		c.setSubcategory(subList);
		return s;
	}
	
	// Read
	@GetMapping("/api/subcategories")
	public List<SubCategory> getAll() {
		return sS.getAll();
	}
	
	// Update
	@PutMapping("/api/subcategory/update/{id}")
	public SubCategory update(@PathVariable("id") Long subId, SubCategory subCategory) {
		return sS.update(subId, subCategory);
	}
	
	@PostMapping("/api/subcategory/update/budgeted/{id}")
	public String updateBudgeted(@PathVariable("id") Long subId, @RequestParam(value="amount") double amount) {
		System.out.println(amount);
		sS.updateBudgeted(subId, amount);
		return "redirect:/budget";
	}
	
	// Delete
	@DeleteMapping("/api/subcategory/delete/{id}")
	public void delete(@PathVariable("id") Long subId) {
		sS.delete(subId);
	}
	
}
