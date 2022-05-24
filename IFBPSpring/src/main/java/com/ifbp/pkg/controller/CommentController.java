package com.ifbp.pkg.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifbp.pkg.jsonObjects.ResponseObject;
import com.ifbp.pkg.repository.AccountRepository;
import com.ifbp.pkg.repository.CommentRepository;
import com.ifbp.pkg.repository.MovieRepository;

import model.Account;
import model.Comment;
import model.CommentPK;
import model.Movie;

@Controller
@Transactional
@RequestMapping(value = "/comment")
public class CommentController {

	@Autowired
	private AccountRepository ar;
	@Autowired
	private MovieRepository mr;
	@Autowired
	private CommentRepository cr;

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/saveCommentRest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseObject saveComment(int accountId, int movieId, String comment) {
		ResponseObject ro = new ResponseObject();
		Comment c = new Comment();
		
		Optional<Account> oa = ar.findById(accountId);
		if(!oa.isEmpty()) {
			c.setAccount(oa.get());
		} else {
			ro.setMessage("No account with ID:" + accountId);
			return ro;
		}
		
		Optional<Movie> om = mr.findById(movieId);
		if(!om.isEmpty()) {
			c.setMovie(om.get());
		} else {
			ro.setMessage("No movie with ID:" + movieId);
			return ro;
		}
		
		c.setComment(comment);
		CommentPK cpk = new CommentPK();
		cpk.setAccountIdAccount(accountId);
		cpk.setMovieIdMovie(movieId);
		c.setId(cpk);
		
		if(cr.save(c) != null) {
			ro.setMessage("Successfully created new comment!");
		} else {
			ro.setMessage("Problem with db.");
		}
		
		return ro;
	}

}
