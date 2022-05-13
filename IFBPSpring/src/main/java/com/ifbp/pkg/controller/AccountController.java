package com.ifbp.pkg.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifbp.pkg.repository.AccountRepository;

import model.Account;

@Controller
@RequestMapping(value = "/user")
public class AccountController {

	@Autowired
	AccountRepository ar;

	@RequestMapping(value = "/saveKorisnikRest", method = RequestMethod.POST)
	public void saveKorisnik(String username, String password) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

		Account acc = new Account();
		acc.setUsername(username);
		acc.setPassword(bcpe.encode(password));

		ar.save(acc);
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String login(String username, String password) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

		Optional<Account> acc = ar.findByUsername(username);

		if (acc.isEmpty()) {
			return "No Account with username:" + username;
		}

		if (acc.get().getPassword().equals(bcpe.encode(password))) {
			return "Successfully logged in!";
		} else {
			return "Wrong password!";
		}
	}

}
