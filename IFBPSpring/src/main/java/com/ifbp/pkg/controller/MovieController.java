package com.ifbp.pkg.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifbp.pkg.jsonObjects.MovieOTJ;
import com.ifbp.pkg.repository.MovieRepository;

import model.Account;
import model.Movie;

@Controller
@Transactional
@RequestMapping(value = "/movie")
public class MovieController {

	@Autowired
	private MovieRepository mr;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/saveMovieRest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveMovieRest(@RequestBody @Valid Movie m, @RequestParam(value="idAccount") Integer idAccount) {
		m.setAccount(new Account());
		m.getAccount().setIdAccount(idAccount);
		
		mr.saveAndFlush(m);
		
		return "Success!";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllMoviesRest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Movie> getAll() {
		List<Movie> movies = mr.findAll();
		for(Movie movie : movies) {
			Account acc = new Account();
			acc.setIdAccount(movie.getAccount().getIdAccount());
			movie.setAccount(acc);
		}
		
		return movies;
	}

	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@RequestMapping(value = "/getMovieDetailsRest/{movieId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Movie getMovieDetails(@PathVariable(value="movieId") int id) {
		Movie m =  mr.findById(id).get();
		
		m.setAccount(null);
		
		return m;
	}

	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@RequestMapping(value = "/getMovieByAgeRatingRest", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Movie> getMovieByAgeRatingRest(String ageRating) {
		List<Movie> allMovies = mr.findAll();
		List<Movie> curatedList = allMovies.stream().filter(movie -> movie.getRating() == ageRating).collect(Collectors.toList());
		
		return curatedList;
	}

	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@RequestMapping(value = "/getMovieLessThanLengthRest", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MovieOTJ> getMovieLessThanLengthRest(String timeValue) {
		List<Movie> allMovies = mr.findAll();
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.US);
		List<MovieOTJ> curatedList = new ArrayList<MovieOTJ>();
		
		Iterator<Movie> i = allMovies.iterator();
		
		while(i.hasNext()) {
			Movie curr = i.next();
			try {
				if(sdf.parse(curr.getLength()).before(sdf.parse(timeValue))) {
					MovieOTJ motj = new MovieOTJ();
//					motj.setAccount(curr.getAccount());
//					motj.setActors(curr.getActors());
//					motj.setComments(curr.getComments());
					motj.setDescription(curr.getDescription());
					motj.setGenres(curr.getGenres());
					motj.setIdMovie(curr.getIdMovie());
					motj.setImageLink(curr.getImageLink());
					motj.setLength(curr.getLength());
//					motj.setPremieres(curr.getPremieres());
					motj.setRating(curr.getRating());
					motj.setTitle(curr.getTitle());
					motj.setVideoLink(curr.getVideoLink());
					motj.setYearOfRelease(curr.getYearOfRelease());
					
					curatedList.add(motj);
				}
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
		}
				
		return curatedList;
	}

	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@RequestMapping(value = "/getMovieMoreThanLengthRest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MovieOTJ> getMovieMoreThanLengthRest(String timeValue) {
		List<Movie> allMovies = mr.findAll();
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.US);
		List<MovieOTJ> curatedList = new ArrayList<MovieOTJ>();
		
		Iterator<Movie> i = allMovies.iterator();
		
		while(i.hasNext()) {
			Movie curr = i.next();
			try {
				if(sdf.parse(curr.getLength()).after(sdf.parse(timeValue))) {
					MovieOTJ motj = new MovieOTJ();
//					motj.setAccount(curr.getAccount());
//					motj.setActors(curr.getActors());
//					motj.setComments(curr.getComments());
					motj.setDescription(curr.getDescription());
					motj.setGenres(curr.getGenres());
					motj.setIdMovie(curr.getIdMovie());
					motj.setImageLink(curr.getImageLink());
					motj.setLength(curr.getLength());
//					motj.setPremieres(curr.getPremieres());
					motj.setRating(curr.getRating());
					motj.setTitle(curr.getTitle());
					motj.setVideoLink(curr.getVideoLink());
					motj.setYearOfRelease(curr.getYearOfRelease());
					
					curatedList.add(motj);
				}
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
		}
		
		return curatedList;
	}

	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@RequestMapping(value = "/getMovieAfterReleaseDateRest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MovieOTJ> getMovieAfterReleaseDateRest(String yearOfRelease) {
		List<Movie> allMovies = mr.findAll();
		
		List<Movie> curatedList = allMovies.stream().filter((movie) -> Integer.parseInt(movie.getYearOfRelease()) >= Integer.parseInt(yearOfRelease)).toList();
		List<MovieOTJ> curatedMOTJList = new ArrayList<>();
		
		for(Movie curr : curatedList) {
			MovieOTJ motj = new MovieOTJ();
			motj.setDescription(curr.getDescription());
			motj.setGenres(curr.getGenres());
			motj.setIdMovie(curr.getIdMovie());
			motj.setImageLink(curr.getImageLink());
			motj.setLength(curr.getLength());
			motj.setRating(curr.getRating());
			motj.setTitle(curr.getTitle());
			motj.setVideoLink(curr.getVideoLink());
			motj.setYearOfRelease(curr.getYearOfRelease());
			
			curatedMOTJList.add(motj);
		}
		
		return curatedMOTJList;
	}

	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@RequestMapping(value = "/getMovieBeforeReleaseDateRest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MovieOTJ> getMovieBeforeReleaseDateRest(String yearOfRelease) {
		List<Movie> allMovies = mr.findAll();
		
		List<Movie> curatedList = allMovies.stream().filter((movie) -> Integer.parseInt(movie.getYearOfRelease()) <= Integer.parseInt(yearOfRelease)).toList();
		List<MovieOTJ> curatedMOTJList = new ArrayList<>();
		
		for(Movie curr : curatedList) {
			MovieOTJ motj = new MovieOTJ();
			motj.setDescription(curr.getDescription());
			motj.setGenres(curr.getGenres());
			motj.setIdMovie(curr.getIdMovie());
			motj.setImageLink(curr.getImageLink());
			motj.setLength(curr.getLength());
			motj.setRating(curr.getRating());
			motj.setTitle(curr.getTitle());
			motj.setVideoLink(curr.getVideoLink());
			motj.setYearOfRelease(curr.getYearOfRelease());
			
			curatedMOTJList.add(motj);
		}
		
		return curatedMOTJList;
	}

}
