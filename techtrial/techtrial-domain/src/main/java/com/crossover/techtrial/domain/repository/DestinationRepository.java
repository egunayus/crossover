package com.crossover.techtrial.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.crossover.techtrial.domain.model.Destination;

public interface DestinationRepository extends CrudRepository<Destination, Long> {

	public Destination findByName(String name);
	
	public Iterable<Destination> findAllByNameContains(String name);
	
}
