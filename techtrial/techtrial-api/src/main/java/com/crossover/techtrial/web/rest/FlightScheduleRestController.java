package com.crossover.techtrial.web.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.domain.model.Destination;
import com.crossover.techtrial.domain.model.FlightSchedule;
import com.crossover.techtrial.domain.repository.DestinationRepository;
import com.crossover.techtrial.domain.repository.FlightScheduleRepository;

@RestController
@RequestMapping("/flightSchedule")
public class FlightScheduleRestController {

	@Autowired
	FlightScheduleRepository flightScheduleRepository;
	
	@Autowired
	DestinationRepository destinationRepository;
	
	/**
	 * 
	 * 
	 * @param from
	 * @param to
	 * @param tripDate
	 * @param returnTripDate
	 * @return
	 */
	@RequestMapping("/search")
	public Iterable<FlightSchedule> searchFlights(
			@RequestParam("from") Long from,
			@RequestParam("to") Long to,
			@RequestParam(name="tripDate", required=false) @DateTimeFormat(iso=ISO.DATE) Date tripDate,
			@RequestParam(name="returnTripDate", required=false) @DateTimeFormat(iso=ISO.DATE) Date returnTripDate
			) {
		
		Destination fromDestination = destinationRepository.findOne(from);
		Destination toDestination = destinationRepository.findOne(to);
		
		Assert.notNull(fromDestination);
		Assert.notNull(toDestination);
		
		return  flightScheduleRepository.searchFlights(fromDestination, toDestination);
	}
	
}
