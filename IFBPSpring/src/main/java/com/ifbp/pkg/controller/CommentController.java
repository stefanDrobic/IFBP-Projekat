package com.ifbp.pkg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ifbp.pkg.repository.AccountRepository;
import com.ifbp.pkg.repository.CommentRepository;
import com.ifbp.pkg.repository.MovieRepository;

import model.Account;
import model.Comment;
import model.Movie;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {
	
	@Autowired
	private AccountRepository ar;
	@Autowired
	private MovieRepository mr;
	@Autowired
	private CommentRepository cr;

	//TODO: Testirati sa frontom
	@RequestMapping(value = "/saveCommentRest", method = RequestMethod.POST)
	public boolean saveComment(int accountId, int movieId, String comment) {
		Movie searchedMovie = mr.findById(movieId).get();
		Account acc = ar.findById(movieId).get();
		
		Comment c = null;
		if(acc != null && searchedMovie != null) {
			c = new Comment();
		} else {
			return false;
		}
		
		c.setAccount(acc);
		c.setMovie(searchedMovie);
		c.setComment(comment);
		
		cr.save(c);
		return true;
	}
	
}
