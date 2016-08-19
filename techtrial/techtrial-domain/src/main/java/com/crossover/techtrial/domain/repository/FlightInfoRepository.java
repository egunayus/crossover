package com.crossover.techtrial.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.crossover.techtrial.domain.model.FlightInfo;

public interface FlightInfoRepository extends CrudRepository<FlightInfo, Long> {

	public Iterable<FlightInfo> findAll(Pageable pageable);
	
}
