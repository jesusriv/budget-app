package com.jesusrivera.budgettest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesusrivera.budgettest.models.Budget;
import com.jesusrivera.budgettest.models.SubCategory;
import com.jesusrivera.budgettest.repositories.BudgetRepository;
import com.jesusrivera.budgettest.repositories.SubCategoryRepository;

@Service
public class SubCategoryService {

	@Autowired
	private SubCategoryRepository sR;
	@Autowired
	private BudgetRepository bR;
	
	public SubCategory create(String name) {
		SubCategory s = new SubCategory(name, 0.00, 0.00);
		return sR.save(s);
	}
	
	public List<SubCategory> getAll() {
		return sR.findAll();
	}
	
	public SubCategory findById(Long id) {
		Optional<SubCategory> oS = sR.findById(id);
		if (oS.isPresent()) {
			SubCategory s = oS.get();
			return s;
		}
		return null;
	}
	
	public List<SubCategory> getByBudget(Long id) {
		Optional<Budget> b = bR.findById(id);
		if (b.isPresent()) {
			Budget budget = b.get();
			return sR.findByBudget(budget);
		}
		return null;
	}
	
	public SubCategory update(Long id, SubCategory sC) {
		Optional<SubCategory> oS = sR.findById(id);
		if (oS.isPresent()) {
			SubCategory s = oS.get();
			s.setName(sC.getName());
			s.setAvailable(sC.getAvailable());
			s.setActivity(sC.getActivity());
			s.setBudgeted(sC.getBudgeted());
			sR.save(s);
			return s;
		}
		return null;
	}
	
	public SubCategory updateBudgeted(Long id, double amount) {
		Optional<SubCategory> oS = sR.findById(id);
		if (oS.isPresent()) {
			SubCategory s = oS.get();
			s.setAvailable(amount);
			s.setBudgeted(amount);
			sR.save(s);
			return s;
		}
		return null;
	}
	
	public void delete(Long id) {
		Optional<SubCategory> oS = sR.findById(id);
		if (oS.isPresent()) {
			SubCategory s = oS.get();
			sR.delete(s);
		}
	}
	
}
