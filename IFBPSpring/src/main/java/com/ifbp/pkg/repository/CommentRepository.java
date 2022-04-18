package com.ifbp.pkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
}
