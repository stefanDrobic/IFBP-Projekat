package com.ifbp.pkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}