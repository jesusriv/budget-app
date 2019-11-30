package com.jesusrivera.budgettest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesusrivera.budgettest.models.BankAccount;
import com.jesusrivera.budgettest.models.User;
import com.jesusrivera.budgettest.repositories.BankAccountRepository;

@Service
public class BankAccountService {

	@Autowired
	private BankAccountRepository bAR;
	
	public BankAccount create(BankAccount ba) {
		BankAccount b = bAR.save(ba);
		bAR.save(b);
		return b;
	}
	
	public List<BankAccount> getAll() {
		return bAR.findAll();
	}
	
	public BankAccount findById(Long id) {
		Optional<BankAccount> oB = bAR.findById(id);
		if (oB.isPresent()) {
			BankAccount b = oB.get();
			return b;	
		}
		return null;
	}
	
	public List<BankAccount> findByUser(User user) {
		return bAR.findByUser(user);
	}
	
	public BankAccount update(Long id, BankAccount bA) {
		Optional<BankAccount> oB = bAR.findById(id);
		if (oB.isPresent()) {
			BankAccount b = oB.get();
			b.setBalance(bA.getBalance());
			b.setBankName(bA.getBankName());
			b.setTypeOfAccount(b.getTypeOfAccount());
			bAR.save(b);
			return b;	
		}
		return null;
	}
	
	public void delete(Long id) {
		Optional<BankAccount> oB = bAR.findById(id);
		if (oB.isPresent()) {
			BankAccount b = oB.get();
			bAR.delete(b);
		}
	}
	
}
