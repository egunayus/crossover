package com.crossover.techtrial.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crossover.techtrial.domain.model.user.User;
import com.crossover.techtrial.domain.repository.user.UserRepository;

@Component
public class UserUtility {

	@Autowired
	UserRepository userRepository;
	
	public User getCurrentUser() {
		//TODO - get this from security context
		User user = userRepository.findOne(1L);

		return user;
	}
}
