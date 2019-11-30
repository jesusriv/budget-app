package com.jesusrivera.budgettest.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jesusrivera.budgettest.models.SubCategory;
import com.jesusrivera.budgettest.models.Transaction;
import com.jesusrivera.budgettest.repositories.SubCategoryRepository;
import com.jesusrivera.budgettest.services.SubCategoryService;
import com.jesusrivera.budgettest.services.TransactionService;

@Controller
public class TransactionController {

	@Autowired
	private TransactionService tS;
	@Autowired
	private SubCategoryService sS;
	@Autowired
	private SubCategoryRepository sR;
	
	@PostMapping("/api/bankaccounts/newTransaction/{bankid}")
	public String newTransaction(@PathVariable("bankid") Long id, 
			@Valid @ModelAttribute("transaction") Transaction t) {
		Transaction tr = tS.newTransaction(id, t);
		SubCategory s = sS.findById(tr.getSubcategory().getId());
		s.setActivity(tr.getAmount() * -1);
		s.setAvailable(s.getAvailable() - tr.getAmount());
		sR.save(s);
		return "redirect:/budget";
	}
	
}
