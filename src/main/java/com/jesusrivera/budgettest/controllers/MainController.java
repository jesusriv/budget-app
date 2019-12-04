package com.jesusrivera.budgettest.controllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.jesusrivera.budgettest.models.BankAccount;
import com.jesusrivera.budgettest.models.Budget;
import com.jesusrivera.budgettest.models.Category;
import com.jesusrivera.budgettest.models.SubCategory;
import com.jesusrivera.budgettest.models.Transaction;
import com.jesusrivera.budgettest.models.User;
import com.jesusrivera.budgettest.services.BankAccountService;
import com.jesusrivera.budgettest.services.BudgetService;
import com.jesusrivera.budgettest.services.CategoryService;
import com.jesusrivera.budgettest.services.TransactionService;
import com.jesusrivera.budgettest.services.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService uS;
	@Autowired
	private BudgetService bS;
	@Autowired
	private CategoryService cS;
	@Autowired
	private BankAccountService bAS;
	@Autowired
	private TransactionService tS;
	
	@GetMapping("/")
	public String index(@ModelAttribute("user") User user) {
		return "index.jsp";
	}
	
	@GetMapping("/budget")
	public String budget(HttpSession session, Model model,
			@ModelAttribute("bankAccount") BankAccount bankAccount) {
		if (session.isNew()) {
			return "index.jsp";
		}
		NumberFormat formatter = new DecimalFormat("#,###,##0.00");
		
		User u = uS.findById((Long) session.getAttribute("userId"));
		Budget budget = u.getBudget();
		List<Category> c = cS.findAll();
		List<SubCategory> sC = budget.getSubCategories();
		List<BankAccount> bA = u.getBankAccounts();
		System.out.println(bA);
		
		String available = bS.getAvailableToBudget(budget.getId());
		Double totalBudget = (Double) budget.getTotalInBudget();
		String totalInBudget = formatter.format(totalBudget.doubleValue());
		
		model.addAttribute("user", u);
		model.addAttribute("budget", budget);
		model.addAttribute("categories", c);
		model.addAttribute("sub", sC);
		model.addAttribute("banks", bA);
		model.addAttribute("budgetTotal", totalInBudget);
		model.addAttribute("formatter", formatter);
		model.addAttribute("available", available);
		return "budget.jsp";
	}
	
	@GetMapping("/form/{id}")
	public String form(@PathVariable("id") Long id, HttpSession session) {
		session.setAttribute("form", id);
		return "redirect:/budget";
	}
	
	@GetMapping("/bankaccount/{id}")
	public String bankaccount(@PathVariable("id") Long id, HttpSession session, Model model,
			@ModelAttribute("transaction") Transaction transaction) {
		if (session.isNew()) {
			return "index.jsp";
		}
		NumberFormat formatter = new DecimalFormat("#0.00");
		NumberFormat thousand = new DecimalFormat("#0,000.00");
		User u = uS.findById((Long) session.getAttribute("userId"));
		BankAccount account = bAS.findById(id);
		List<BankAccount> bA = bAS.findByUser(u);
		List<Transaction> t = tS.findAll();
		List<Budget> b = bS.findByUser((Long) session.getAttribute("userId"));
		Budget bb = b.get(0);
		List<SubCategory> sC = bb.getSubCategories();
		double available = 0.00;
		double totalForBudget = 0.00;
		
		for (int i = 0; i < bA.size(); i++) {
			available += bA.get(i).getBalance();
		}
		totalForBudget += available;
		for (int i = 0; i < sC.size(); i++) {
			available -= sC.get(i).getBudgeted();
		}
		
		
		model.addAttribute("user", u);
		model.addAttribute("budget", bb);
		model.addAttribute("banks", bA);
		model.addAttribute("budgetTotal", totalForBudget);
		model.addAttribute("account", account);
		model.addAttribute("transactions", t);
		model.addAttribute("available", available);
		model.addAttribute("formatter", formatter);
		model.addAttribute("thousand", thousand);
		return "accounts.jsp";
	}
}

