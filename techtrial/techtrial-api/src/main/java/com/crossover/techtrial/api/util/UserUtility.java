package com.crossover.techtrial.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.crossover.techtrial.api.rest.security.model.ApiUserDetails;
import com.crossover.techtrial.domain.model.user.User;
import com.crossover.techtrial.domain.repository.user.UserRepository;

@Component
public class UserUtility {

	@Autowired
	UserRepository userRepository;
	
	public User getCurrentUser() {
		ApiUserDetails apiUserDetails = (ApiUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return apiUserDetails.getUser();
	}
	
	public String getAuthToken() {
		String authToken = (String) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		return authToken;
	}
}
