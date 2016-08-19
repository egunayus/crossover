package com.crossover.techtrial.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Destination extends AbstractBaseEntity {

	@Column(length=100, unique=true, nullable=false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
