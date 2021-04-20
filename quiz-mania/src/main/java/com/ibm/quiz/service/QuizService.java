package com.ibm.quiz.service;

import com.ibm.quiz.entity.Option;
import com.ibm.quiz.entity.Question;
import com.ibm.quiz.entity.Quiz;

public interface QuizService {

	int addQuiz(Quiz quiz);
	
	Quiz getQuiz(int qcode);
	
	int addQuestion(Question que, int qcode);
	
	int addOption(Option opt, int qid);
	
	String submitQuiz(Quiz q);
	
	Question getQuestion(int qid);
}
