package com.ibm.quiz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.quiz.entity.User;
import com.ibm.quiz.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping(value = "/add/{name}")
	public String addUser(@PathVariable("name") String name) {
		User u = new User();
		u.setUsername(name);
		int uid = service.addUser(u);
		return "User added with ID : " + uid;
	}
	
//	@PostMapping(value = "/add")
//	public String addUserParam(@RequestParam("name") String name) {
//		User u = new User();
//		u.setUsername(name);
//		int uid = service.addUser(u);
//		return "User added with ID : " + uid;
//	}
//	
//	@PostMapping(value = "/add", consumes = "application/json")
//	public String addUserBody(@RequestBody User u) {
//		int uid = service.addUser(u);
//		return "User added with ID : " + uid;
//	}
	
	@GetMapping(value = "/get/{uid}", produces = "application/json")
	public User getUser(@PathVariable("uid") int uid) {
		User u = service.getUser(uid);
		return u;
	}
	
	

}
