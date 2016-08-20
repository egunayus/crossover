package com.crossover.techtrial.domain.repository.flight;

import org.springframework.data.repository.CrudRepository;

import com.crossover.techtrial.domain.model.flight.Destination;

public interface DestinationRepository extends CrudRepository<Destination, Long> {

	public Destination findByName(String name);
	
	public Iterable<Destination> findAllByNameContains(String name);
	
}
