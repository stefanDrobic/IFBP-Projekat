package com.ifbp.pkg.repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	public List<Movie> findByAgeRating(String ageRating);
	public List<Movie> findByLengthGreaterThan(Time length);
	public List<Movie> findByLengthLessThan(Time length);
	public List<Movie> findByReleaseDateGreaterThan(Date releaseDate);
	public List<Movie> findByReleaseDateLessThan(Date releaseDate);


}
