package com.jesusrivera.budgettest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jesusrivera.budgettest.models.BankAccount;
import com.jesusrivera.budgettest.models.User;
import com.jesusrivera.budgettest.repositories.BankAccountRepository;
import com.jesusrivera.budgettest.repositories.UserRepository;
import com.jesusrivera.budgettest.services.BankAccountService;
import com.jesusrivera.budgettest.services.UserService;

@Controller
public class BankAccountController {
	
	@Autowired
	private BankAccountService bAS;
	@Autowired
	private UserService uS;
	@Autowired
	private UserRepository uR;
	@Autowired
	private BankAccountRepository bAR;
	
	@PostMapping("/api/create/bankaccount/{id}/{budgetid}")
	public String create(@Valid @ModelAttribute("bankaccount") BankAccount bA, BindingResult result, @PathVariable("id") Long id, @PathVariable("budgetid") Long budgetid) {
		if (result.hasErrors()) {
			return null;
		}
		
		BankAccount b = bAS.create(bA);
		User u = uS.findById(id);
		u.getBankAccounts().add(b);
		b.setUser(u);
		bAR.save(b);
		uR.save(u);
		return "redirect:/budget";
	}
	
	@GetMapping("/api/bankaccounts")
	public List<BankAccount> getAll() {
		return bAS.getAll();
	}
	
	@PutMapping("/api/bankaccounts/update/{id}")
	public BankAccount update(@PathVariable("id") Long id, BankAccount bA) {
		return bAS.update(id, bA);
	}
	
	@DeleteMapping("/api/bankaccounts/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		bAS.delete(id);
	}
	
}
