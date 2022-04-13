package com.ifbp.pkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
