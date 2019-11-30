package com.jesusrivera.budgettest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jesusrivera.budgettest.models.BankAccount;
import com.jesusrivera.budgettest.models.User;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {

	public List<BankAccount> findAll();
	
	public List<BankAccount> findByUser(User user);
}
