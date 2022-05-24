package com.ifbp.pkg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifbp.pkg.jsonObjects.ResponseObject;
import com.ifbp.pkg.repository.AccountRepository;
import com.ifbp.pkg.repository.MovieTriviaRepository;

import model.Account;
import model.MovieTrivia;

@Controller
@RequestMapping(value = "/movietr")
public class MovieTriviaController {

	@Autowired
	MovieTriviaRepository mtr;
	
	@Autowired
	AccountRepository ar;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getHomeItemsRest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MovieTrivia> getHomeItems() {
		List<MovieTrivia> trivias = mtr.findAll();
		List<MovieTrivia> curatedList = new ArrayList<>();
		
		for(MovieTrivia mt : trivias) {
			mt.setAccount(null);
			curatedList.add(mt);
		}
		
		return curatedList;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createHomeItemRest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseObject createHomeItem(String title, String description, String image_url, String video_url, Integer account_id_account) {
		MovieTrivia mt = new MovieTrivia();
		
		mt.setTitle(title);
		mt.setDescription(description);
		mt.setImageUrl(image_url);
		mt.setVideoUrl(video_url);
		
		ResponseObject chiro = new ResponseObject();
		
		Optional<Account> o = ar.findById(account_id_account);
		if(!o.isEmpty()) {
			mt.setAccount(o.get());
			chiro.setMessage("Successfully saved Movie Trivia!");
			mtr.save(mt);
		} else {
			chiro.setMessage("Successfully saved Movie Trivia!");
		}
		
		return chiro;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getHomeItemByNameRest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MovieTrivia getHomeItemByName(String name) {
		Optional<MovieTrivia> omt = mtr.findByTitle(name);

		if(!omt.isEmpty()) {
			MovieTrivia mt = omt.get();
			mt.setAccount(null);
			return omt.get();
		} else {
			return null;
		}
	}
	
}
