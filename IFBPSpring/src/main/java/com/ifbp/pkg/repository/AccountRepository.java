package com.ifbp.pkg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Optional<Account> findByUsername(String username);
	
}
