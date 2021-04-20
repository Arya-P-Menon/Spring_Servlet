package com.ibm.quiz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.quiz.entity.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer>{

}
