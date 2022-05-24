package com.ifbp.pkg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.MovieTrivia;

public interface MovieTriviaRepository extends JpaRepository<MovieTrivia, Integer> {

	public Optional<MovieTrivia> findByTitle(String title);
	
}
