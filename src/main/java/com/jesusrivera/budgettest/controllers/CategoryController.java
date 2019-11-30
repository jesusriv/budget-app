package com.jesusrivera.budgettest.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesusrivera.budgettest.models.Category;
import com.jesusrivera.budgettest.services.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService cS;

	@PostMapping("/api/create/category")
	public Category create(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		return cS.createNew(category);
	}
	
}
