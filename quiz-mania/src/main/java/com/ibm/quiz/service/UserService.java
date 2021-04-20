package com.ibm.quiz.service;

import com.ibm.quiz.entity.User;

public interface UserService {
	

	int addUser(User user);
	
	User getUser(int uid);

}
