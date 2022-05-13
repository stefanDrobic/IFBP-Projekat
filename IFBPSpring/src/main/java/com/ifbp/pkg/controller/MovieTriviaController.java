package com.ifbp.pkg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifbp.pkg.repository.AccountRepository;
import com.ifbp.pkg.repository.MovieTriviaRepository;

import model.MovieTrivia;

@Controller
@RequestMapping(value = "/movietr")
public class MovieTriviaController {

	@Autowired
	MovieTriviaRepository mtr;
	
	@Autowired
	AccountRepository ar;
	
	@RequestMapping(value = "/getHomeItemsRest")
	public Iterable<MovieTrivia> getHomeItems() {
		return mtr.findAll();
	}
	
//	@RequestMapping(value = "/createHomeItemRest")
//	public void createHomeItem(String description, String videoLink, String username) {
//		MovieTrivia mt = new MovieTrivia();
//		mt.setDescription(description);
//		mt.setVideoLink(videoLink);
//		mt.setAccount(ar.findByUsername(username));
//		
//		mtr.save(mt);
//	}
	
//	@RequestMapping(value = "/getHomeItemByNameRest")
//	public Iterable<MovieTrivia> getHomeItemByName(String name) {
//		return mtr.findBy
//	}
	
}
