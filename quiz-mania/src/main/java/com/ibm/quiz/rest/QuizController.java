package com.ibm.quiz.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.quiz.entity.Option;
import com.ibm.quiz.entity.Question;
import com.ibm.quiz.entity.Quiz;
import com.ibm.quiz.entity.User;
import com.ibm.quiz.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	private QuizService service;
	
	@PostMapping(value = "/create/{topic}")
	public String newQuiz(@PathVariable("topic") String topic, HttpSession session) {
		User user = (User) session.getAttribute("USER");
		if(user!=null && user.getRole().equals("Admin")) {
			Quiz q = new Quiz();
			q.setTopic(topic);
			int qcode = service.addQuiz(q);
			return "Quiz created for " + topic + " with code " + qcode;
		}else
			return "Only an Admin can create Quiz";
	}
	
	@PostMapping(value = "/addQ/{qcode}", consumes = "application/json")
	public String addQuestion(@RequestBody Question que, @PathVariable("qcode") int qcode) {
		int qid = service.addQuestion(que, qcode);
		return "Question is added with ID : "+ qid;
	}
	
	@PostMapping(value = "/addOpt/{qid}", consumes = "application/json")
	public String addOptions(@RequestBody Option opt, @PathVariable("qid") int qid) {
		int opid = service.addOption(opt, qid);
		return "Option is added with ID : "+ opid;
	}
	
	@GetMapping(value = "/getQues/{qid}", produces = "application/json")
	public Question getQuestion(@PathVariable int qid) {
		return service.getQuestion(qid);
	}
	
	@GetMapping(value = "/getQuiz/{qcode}", produces = "application/json")
	public ResponseEntity<?> getQuiz(@PathVariable int qcode, HttpSession session) {
		if(session.getAttribute("USER")!=null)
			return new ResponseEntity<Quiz>(service.getQuiz(qcode), HttpStatus.OK);
		else
			return new ResponseEntity<String>("Sorry! You are not logged in", HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping(value = "/submit", consumes = "application/json")
	public String submitQuiz(@RequestBody Quiz q) {
		return service.submitQuiz(q);
	}
}
