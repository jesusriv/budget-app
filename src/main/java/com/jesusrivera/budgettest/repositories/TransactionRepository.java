package com.jesusrivera.budgettest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jesusrivera.budgettest.models.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	public List<Transaction> findAll();
	
}
