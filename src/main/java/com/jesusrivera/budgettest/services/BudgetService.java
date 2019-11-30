package com.jesusrivera.budgettest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesusrivera.budgettest.models.Budget;
import com.jesusrivera.budgettest.models.Category;
import com.jesusrivera.budgettest.models.SubCategory;
import com.jesusrivera.budgettest.repositories.BudgetRepository;

@Service
public class BudgetService {

	@Autowired
	private BudgetRepository bR;
	@Autowired
	public SubCategoryService sS;
	@Autowired
	public CategoryService cS;
	@Autowired
	public UserService uS;
	
	public Budget createForRegistration(Long id) {
		Budget b = new Budget();
		bR.save(b);
		List<SubCategory> sL = new ArrayList<SubCategory>();
		
		Category iO = cS.findByName("Immediate Obligations");
		SubCategory s = sS.create("Rent");
		s.setCategory(iO);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Phone");
		s.setCategory(iO);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Groceries");
		s.setCategory(iO);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Transportation");
		s.setCategory(iO);
		s.setBudget(b);
		sL.add(s);
		
		Category tE = cS.findByName("True Expenses");
		s = sS.create("Personal Care");
		s.setCategory(tE);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Medical");
		s.setCategory(tE);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Clothing");
		s.setCategory(tE);
		s.setBudget(b);
		s = sS.create("Giving");
		s.setCategory(tE);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Gifts");
		s.setCategory(tE);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Software Subscription");
		s.setCategory(tE);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Stuff I Forgot To Budget For");
		s.setCategory(tE);
		s.setBudget(b);
		sL.add(s);
		
		Category dP = cS.findByName("Debt Payments");
		s = sS.create("Tuition");
		s.setCategory(dP);
		s.setBudget(b);
		sL.add(s);
		
		Category cP = cS.findByName("Credit Card Payments");
		s = sS.create("Master Card");
		s.setCategory(cP);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Discover CC");
		s.setCategory(cP);
		s.setBudget(b);
		sL.add(s);
		
		Category qL = cS.findByName("Quality of Life Goals");
		s = sS.create("Expense Saving");
		s.setCategory(qL);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Rainy Day");
		s.setCategory(qL);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Car Savings");
		s.setCategory(qL);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Home Savings");
		s.setCategory(qL);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Vaction");
		s.setCategory(qL);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("School");
		s.setCategory(qL);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Fitness");
		s.setCategory(qL);
		s.setBudget(b);
		sL.add(s);
		
		Category jF = cS.findByName("Just for Fun");
		s = sS.create("Dining Out");
		s.setCategory(jF);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Gaming");
		s.setCategory(jF);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Concerts");
		s.setCategory(jF);
		s.setBudget(b);
		sL.add(s);
		s = sS.create("Fun Money");
		s.setCategory(jF);
		s.setBudget(b);
		sL.add(s);
		
		b.setSubCategories(sL);
		b.setUser(uS.findById(id));
		bR.save(b);
		
		return b;
	}
	
	public Budget findById(Long id) {
		Optional<Budget> oB = bR.findById(id);
		if (oB.isPresent()) {
			return oB.get();
		}
		return null;
	}
	
	public List<Budget> getAllBudgets() {
		return bR.findAll();
	}
	
	public Budget updateBudget(Long id, Budget budget) {
		Optional<Budget> oB = bR.findById(id);
		if (oB.isPresent()) {
			Budget b = oB.get();
			b.setName(budget.getName());
			bR.save(b);
			return b;
		}
		return null;
	}
	
	public String delete(Long id) {
		Optional<Budget> oB = bR.findById(id);
		if (oB.isPresent()) {
			Budget b = oB.get();
			bR.delete(b);
			return "success";
		}
		return null;
	}
	
	public List<Budget> findByUser(Long id) {
		List<Budget> b = bR.findByUser(uS.findById(id));
		return b;
	}
}
