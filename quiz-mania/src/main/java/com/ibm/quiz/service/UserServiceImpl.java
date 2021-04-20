package com.ibm.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.quiz.entity.User;
import com.ibm.quiz.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public int addUser(User user) {

		repo.save(user);
		return user.getUid();
	}

	@Override
	public User getUser(int uid) {
		// TODO Auto-generated method stub
		return repo.findById(uid).get();
	}

}
