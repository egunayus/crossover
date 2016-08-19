package com.crossover.techtrial.domain.model;

import javax.persistence.Entity;

@Entity
public class Passenger extends AbstractBaseEntity {

	private String name;
	
	private String surname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	} 
	
	
}
