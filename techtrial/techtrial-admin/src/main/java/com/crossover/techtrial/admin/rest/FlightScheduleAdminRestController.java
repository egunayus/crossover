package com.crossover.techtrial.admin.rest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.validation.Path.CrossParameterNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.domain.model.FlightInfo;
import com.crossover.techtrial.domain.model.FlightSchedule;
import com.crossover.techtrial.domain.model.Plane;
import com.crossover.techtrial.domain.repository.FlightInfoRepository;
import com.crossover.techtrial.domain.repository.FlightScheduleRepository;
import com.crossover.techtrial.domain.service.FlightInfoService;
import com.crossover.techtrial.domain.service.FlightScheduleService;

/**
 * Provides admin API services for managing {@link FlightSchedule} domain data 
 * 
 * @author egunay
 *
 */
@RestController
@RequestMapping("/flightSchedule")
public class FlightScheduleAdminRestController {

	@Autowired
	FlightScheduleRepository flightScheduleRepository;
	
	@Autowired
	FlightScheduleService flightScheduleService;
	
	@Autowired
	FlightInfoRepository flightInfoRepository;

	@RequestMapping(value="/all", method=RequestMethod.GET)
	public Iterable<FlightSchedule> findAllFlightInfoList(
			@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="size", defaultValue="20") Integer size
			) {
		
		PageRequest pageRequest = new PageRequest(page, size);
		
		return flightScheduleRepository.findAll(pageRequest);
	}

	@RequestMapping(value="/scheduleFlights", method=RequestMethod.POST)
	public Iterable<FlightSchedule> addNewFlightSchedules(
			@RequestBody FlightSchedule flightScheduleTemplate,
			@RequestParam(name="numberOfDays") Integer numberOfDays,
			@RequestParam(name="startingFrom") String startingFrom
			) {
		
		LocalDateTime startingFromDateTime = LocalDateTime.parse(startingFrom);
		startingFromDateTime = startingFromDateTime.truncatedTo(ChronoUnit.MINUTES);
		
		return flightScheduleService.addNewFlightSchedules(flightScheduleTemplate, numberOfDays, startingFromDateTime);
	}
	
}
