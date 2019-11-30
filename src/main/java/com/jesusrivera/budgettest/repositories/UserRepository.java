package com.jesusrivera.budgettest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jesusrivera.budgettest.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public List<User> findAll();

	
	public User findByEmail(String email);
}
