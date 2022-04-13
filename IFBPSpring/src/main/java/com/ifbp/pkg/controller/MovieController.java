package com.ifbp.pkg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ifbp.pkg.repository.MovieRepository;

import model.Movie;

@Controller
@RequestMapping(value = "/movie")
public class MovieController {

	@Autowired
	MovieRepository mr;
	
	@RequestMapping(value = "/saveMovie")
	public void saveMovie(@Valid @ModelAttribute("Movie") Movie mov, BindingResult br) {
		if(!br.hasErrors()) {
			mr.save(mov);
		}
	}
	
	//TODO: Testirati sa frontom
	@RequestMapping(value = "/saveMovieRest", method = RequestMethod.POST)
	public void saveMovieRest(@Valid @ModelAttribute("Movie") Movie mov, BindingResult br) {
		this.saveMovie(mov, br);
	}
	
	//TODO: Testirati sa frontom
	public List<Movie> getAll() {
		return mr.findAll();
	}
	
}
