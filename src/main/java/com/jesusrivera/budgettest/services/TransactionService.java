package com.jesusrivera.budgettest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesusrivera.budgettest.models.BankAccount;
import com.jesusrivera.budgettest.models.Transaction;
import com.jesusrivera.budgettest.repositories.BankAccountRepository;
import com.jesusrivera.budgettest.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository tR;
	@Autowired
	private BankAccountService bAS;
	@Autowired
	private BankAccountRepository bAR;
	
	public Transaction newTransaction(Long id, Transaction t) {
		BankAccount b = bAS.findById(id);
		List<Transaction> tList = b.getTransactions();
		tR.save(t);
		if (tList.size() <= 0) {
			tList = new ArrayList<Transaction>();
			tList.add(t);
			b.setTransactions(tList);
			b.setBalance(b.getBalance() - t.getAmount());
			bAR.save(b);			
			return t;
		}
		tList.add(t);
		b.setBalance(b.getBalance() - t.getAmount());
		b.setTransactions(tList);
		bAR.save(b);
		return t;
	}
	
	public List<Transaction> findAll() {
		return tR.findAll();
	}
	
}
