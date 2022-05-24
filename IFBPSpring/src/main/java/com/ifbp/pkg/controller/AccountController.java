package com.ifbp.pkg.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifbp.pkg.jsonObjects.ResponseObject;
import com.ifbp.pkg.repository.AccountRepository;

import model.Account;
import model.Comment;
import model.ForumThread;
import model.Movie;
import model.MovieTrivia;
import model.ThreadResponse;

@Controller
@RequestMapping(value = "/user")
public class AccountController {

	@Autowired
	AccountRepository ar;

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/saveKorisnikRest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseObject saveKorisnik(String username, String password) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder(BCryptVersion.$2A, 10);
		ResponseObject ro = new ResponseObject();
		
		Optional<Account> oa = ar.findByUsername(username);
		if(!oa.isEmpty()) {
			ro.setMessage("Account already exists with username: " + username);
			return ro;
		}
		
		Account acc = new Account();
		
		acc.setUsername(username);
		acc.setPassword(bcpe.encode(password));
		
		acc.setComments(new ArrayList<Comment>());
		acc.setForumThreads(new ArrayList<ForumThread>());
		acc.setMovies(new ArrayList<Movie>());
		acc.setMovieTrivias(new ArrayList<MovieTrivia>());
		acc.setThreadResponses(new ArrayList<ThreadResponse>());
		acc.setIdAccount(-1); //magic

		ar.save(acc);
		
		ro.setMessage("Successfully created account!");
		return ro;
	}
	//pwd je uvek 123, ako ide u produkciju obavezno::
	//TODO OBRISATI!!!

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseObject login(String username, String password) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder(BCryptVersion.$2A, 10);
		ResponseObject ro = new ResponseObject();
		
		Optional<Account> acc = ar.findByUsername(username);

		if (acc.isEmpty()) {
			ro.setMessage("No account with such username[" + username + "] in db");
			return ro;
		}

		if (bcpe.matches(password, acc.get().getPassword())) {
			ro.setMessage("Successfully logged in!");
		} else {
			ro.setMessage("Wrong username and/or password combination.");
		}
		
		return ro;
	}

}
