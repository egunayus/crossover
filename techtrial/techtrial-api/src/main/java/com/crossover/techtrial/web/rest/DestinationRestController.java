package com.crossover.techtrial.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.domain.model.Destination;
import com.crossover.techtrial.domain.repository.DestinationRepository;

@RestController
@RequestMapping("/destination")
public class DestinationRestController {

	@Autowired
	DestinationRepository destinationRepository;
	
	@RequestMapping("/all")
	public Iterable<Destination> findAll() {
		return destinationRepository.findAll();
	}

	@RequestMapping("/contains/{name}")
	public Iterable<Destination> findAllByNameContains(@PathVariable("name") String name) {
		return destinationRepository.findAllByNameContains(name);
	}
	
}
