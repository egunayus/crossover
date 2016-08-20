package com.crossover.techtrial.domain.model.user;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.crossover.techtrial.domain.model.AbstractPersonEntity;

@Entity
public class Passenger extends AbstractPersonEntity {

	/**
	 * references {@link User} to whom the passenger entity belongs to
	 */
	@ManyToOne
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
