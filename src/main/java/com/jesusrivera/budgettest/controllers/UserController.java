package com.jesusrivera.budgettest.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jesusrivera.budgettest.models.User;
import com.jesusrivera.budgettest.services.BudgetService;
import com.jesusrivera.budgettest.services.UserService;
import com.jesusrivera.budgettest.validators.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService uS;
	@Autowired
	private UserValidator uV;
	@Autowired
	private BudgetService bS;

	
	@PostMapping("/api/create/user")
	public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		uV.validate(user, result);
		if (result.hasErrors()) {
			System.out.println("User: " + result.getErrorCount());
			return null;
		}
		User u = uS.create(user);
		bS.createForRegistration(u.getId());
		session.setAttribute("userId", u.getId());
		return "redirect:/budget";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
		boolean isAuthenticated = uS.authenticateUser(email, password);
		if(isAuthenticated) {
			User u = uS.findByEmail(email);
			session.setAttribute("userId", u.getId());
			return "redirect:/budget";
		} 
		model.addAttribute("error", "Invalid Credentials. Please try again.");
		return null;
	}
	
	@GetMapping("/api/users")
	public List<User> getAllUsers() {
		return uS.getAllUsers();
	}
	
	@GetMapping("/api/user/{id}")
	public User oneUser(@PathVariable("id") Long id) {
		return uS.findById(id);
	}
	
	@PutMapping("/api/user/update/{id}")
	public User updateUser(@PathVariable("id") Long id, User user) {
		return uS.updateUser(user, id);
	}
	
	@DeleteMapping("/api/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		String result = uS.deleteUser(id);
		if (result == "succes") {
			return "Succesfully deleted user";
		} 
		return "Something went wrong";
	}
}
