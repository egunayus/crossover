package com.crossover.techtrial.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.crossover.techtrial.domain.model.Plane;

public interface PlaneRepository extends CrudRepository<Plane, Long> {

	public Iterable<Plane> findAll(Pageable pageable);
	
}
