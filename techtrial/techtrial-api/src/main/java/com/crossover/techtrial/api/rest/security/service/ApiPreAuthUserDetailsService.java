package com.crossover.techtrial.api.rest.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.api.rest.security.model.ApiUserDetails;
import com.crossover.techtrial.domain.model.user.User;
import com.crossover.techtrial.domain.repository.user.UserRepository;

/**
 * User details service for pre authenticated users via X-Auth-Token.
 * 
 */
@Service
public class ApiPreAuthUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenVerifyService tokenVerifyService;
	
	@Override
	public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
		String accessToken = (String) token.getPrincipal();
		
		Long userId = tokenVerifyService.getUserId(accessToken);
		if (userId == null)
			throw new UsernameNotFoundException("Token not valid");

		User user = userRepository.findOne(userId);
		if (user == null)
			throw new UsernameNotFoundException("User does not exist");
		
		token.setDetails(accessToken);
		
		ApiUserDetails userDetails = new ApiUserDetails(user);
		return userDetails;
	}
	
}
