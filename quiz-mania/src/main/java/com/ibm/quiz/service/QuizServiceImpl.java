package com.ibm.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.quiz.entity.Option;
import com.ibm.quiz.entity.Question;
import com.ibm.quiz.entity.Quiz;
import com.ibm.quiz.repo.OptionRepo;
import com.ibm.quiz.repo.QuestionRepo;
import com.ibm.quiz.repo.QuizRepo;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepo zrepo;
	
	@Autowired
	private QuestionRepo qrepo;
	
	@Autowired
	private OptionRepo orepo;
	
	@Override
	public int addQuiz(Quiz quiz) {
		zrepo.save(quiz);
		return quiz.getQcode();
	}

	@Override
	public Quiz getQuiz(int qcode) {
		return zrepo.findById(qcode).get();
	}

	@Override
	public int addQuestion(Question que, int qcode) {
		Quiz quiz = zrepo.findById(qcode).get();
		//setting quiz to question and vice-versa
		quiz.getQuestions().add(que);
		que.setQuiz(quiz);
		qrepo.save(que);
		return que.getQid();
	}

	@Override
	public int addOption(Option opt, int qid) {
		// TODO Auto-generated method stub
		Question que = qrepo.findById(qid).get();
		que.getOptions().add(opt);
		opt.setQue(que);
		orepo.save(opt);
		return opt.getOpid();
	}

	@Override
	public String submitQuiz(Quiz q) {
		int qcount = q.getQuestions().size();
		int correct = 0;
		for(Question que : q.getQuestions()) {
			//System.out.println(que.getQuestion() + " : " + que.getAnswer());
			//qcount++;
			for(Option op : que.getOptions()) {
				//System.out.println(op.getOption() + " : " + op.getText());
				if(op.getOption() == que.getAnswer() && op.getFlag()==1)
					correct++;
			}
		}
		System.out.println("Correct Answers = " + correct + " out of "+ qcount);
		if(correct >= qcount*0.6)
			return "Precentage : "+ (correct*100.0)/qcount + " Result : Passed" ;
		else
			return "Precentage : "+ (correct*100.0)/qcount + "Result : Failed" ;

//		double percent = (correct*100.0)/qcount;
//		if(percent >= 60.0)
//			r("Result : Passed" );
//		else
//			System.out.println("Result : Failed" );
			
		
		
	}

	@Override
	public Question getQuestion(int qid) {
		return qrepo.findById(qid).get();
	}

}
