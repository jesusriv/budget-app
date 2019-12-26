package com.jesusrivera.budgettest.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jesusrivera.budgettest.models.Budget;
import com.jesusrivera.budgettest.models.User;
import com.jesusrivera.budgettest.repositories.BudgetRepository;
import com.jesusrivera.budgettest.services.BudgetService;
import com.jesusrivera.budgettest.services.UserService;
import com.jesusrivera.budgettest.validators.UserValidator;

@RestController
public class UserController {

	@Autowired
	private UserService uS;
	@Autowired
	private UserValidator uV;
	@Autowired
	private BudgetService bS;
	@Autowired
	private BudgetRepository bR;

	// GET USER
	@GetMapping("/api/user/{id}")
	public User oneUser(@PathVariable("id") Long id) {
		return uS.findById(id);
	}
	
	// UPDATE USER INFORMATION
	@PutMapping("/api/user/update/{id}")
	public User updateUser(@PathVariable("id") Long id, User user) {
		return uS.updateUser(user, id);
	}
	
	// DELETE USER (ACCOUNT DELETION)
	@DeleteMapping("/api/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		String result = uS.deleteUser(id);
		if (result == "succes") {
			return "Succesfully deleted user";
		} 
		return "Something went wrong";
	}
	
	// CREATE A NEW INSTANCE OF A USER (REGISTRATION)
	@PostMapping("/api/create/user")
	public User createUser(@RequestBody HashMap<String, String> body, BindingResult result, HttpSession session) {
		User user = new User(body.get("name"), body.get("email"), body.get("password"), body.get("confirm"));
		uV.validate(user, result);
		if (result.hasErrors()) {
			System.out.println("User: " + result.getErrorCount());
			return null;
		}
		User u = uS.create(user);
		System.out.println(u);
		Budget budget = bS.createForRegistration(u.getId());
		budget.setDefaultUser(u);
		bR.save(budget);
		session.setAttribute("userId", u.getId());
		return u;
	}
	
	// LOGIN IN AN EXISTING USER
	@PostMapping("/api/login")
	public User loginUser(@RequestBody HashMap<String, String> body, HttpSession session, Model model) {
		
		boolean isAuthenticated = uS.authenticateUser(body.get("email"), body.get("password"));
		if(isAuthenticated) {
			User u = uS.findByEmail(body.get("email"));
			session.setAttribute("userId", u.getId());
			return u;
		} 
		model.addAttribute("error", "Invalid Credentials. Please try again.");
		return null;
	}
	
	// TEST: TBDELETED
	@PostMapping("/api/login/user")
	public User login(@RequestParam(value="email") String email, @RequestParam(value="password") String password, HttpSession session, Model model) {
		boolean isAuthenticated = uS.authenticateUser(email, password);
		if(isAuthenticated) {
			User u = uS.findByEmail(email);
			session.setAttribute("userId", u.getId());
			return u;
		} 
		model.addAttribute("error", "Invalid Credentials. Please try again.");
		return null;
	}
	
	// LOGOUT USER
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
}
