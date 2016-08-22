package com.crossover.techtrial.domain.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.crossover.techtrial.domain.model.user.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);
	
}
