package com.ibm.quiz.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.quiz.bean.Login;
import com.ibm.quiz.entity.User;
import com.ibm.quiz.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService service;
	
//	@PostMapping(value = "/add/{name}")
//	public String addUser(@PathVariable("name") String name) {
//		User u = new User();
//		u.setUsername(name);
//		int uid = service.addUser(u);
//		return "User added with ID : " + uid;
//	}
	
//	@PostMapping(value = "/add")
//	public String addUserParam(@RequestParam("name") String name) {
//		User u = new User();
//		u.setUsername(name);
//		int uid = service.addUser(u);
//		return "User added with ID : " + uid;
//	}
	
	@PostMapping(value = "/add", consumes = "application/json")
	public String addUserBody(@RequestBody User u) {
		int uid = service.addUser(u);
		return "User added with ID : " + uid;
	}
	
	@PostMapping(value = "/auth", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> authenticate(@RequestBody Login login, HttpSession session) {
		User user = service.validate(login);
		if(user!=null) {
			session.setAttribute("USER", user);
			return new ResponseEntity<User>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Invalid Username or Password", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "Logged out successfully";
	}
	
	@GetMapping(value = "/get/{uid}", produces = "application/json")
	public User getUser(@PathVariable("uid") int uid) {
		User u = service.getUser(uid);
		return u;
	}
	
	

}
