package com.crossover.techtrial.admin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.domain.model.Destination;
import com.crossover.techtrial.domain.repository.DestinationRepository;

/**
 * Provides admin API services for managing {@link Destination} domain data 
 * 
 * @author egunay
 *
 */
@RestController
@RequestMapping("/destination")
public class DestinationAdminRestController {

	@Autowired
	DestinationRepository destinationRepository;
	
	/**
	 * returns the collection of all {@link Destination} entities 
	 * 
	 * @return collection of {@link Destination}
	 */
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public Iterable<Destination> findAllDestinations() {
		return destinationRepository.findAll();
	}
	
	/**
	 * upserts a given {@link Destination} entity.  
	 * 
	 * @param destination
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public Destination saveDestination(@RequestBody Destination destination) {
		destinationRepository.save(destination);
		
		return destination;
	}
	
}
