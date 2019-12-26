package com.jesusrivera.budgettest.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jesusrivera.budgettest.models.Category;
import com.jesusrivera.budgettest.models.SubCategory;
import com.jesusrivera.budgettest.services.CategoryService;
import com.jesusrivera.budgettest.services.SubCategoryService;


@RestController
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
	
	@GetMapping("/api/subcategories/{budgetId}")
	public List<SubCategory> getByBudget(@PathVariable("budgetId") Long id) {
		return sS.getByBudget(id);
	}
	
	@GetMapping("/api/subcategory/{id}")
	public SubCategory findBySubId(@PathVariable("id") Long id) {
		return sS.findById(id);
	}
	 
	// Update
	@PutMapping("/api/subcategory/update/{id}")
	public SubCategory update(@PathVariable("id") Long subId, SubCategory subCategory) {
		return sS.update(subId, subCategory);
	}
	
	@PostMapping("/api/subcategory/update/budgeted/{id}")
	public String updateBudgeted(@PathVariable("id") Long subId, @RequestBody HashMap<String, Integer> body) {
		sS.updateBudgeted(subId, body.get("amount"));
		return "Success";
	}
	
	// Delete
	@DeleteMapping("/api/subcategory/delete/{id}")
	public void delete(@PathVariable("id") Long subId) {
		sS.delete(subId);
	}
	
}
