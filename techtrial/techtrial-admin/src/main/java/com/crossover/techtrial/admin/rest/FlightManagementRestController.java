package com.crossover.techtrial.admin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.domain.model.Destination;
import com.crossover.techtrial.domain.model.FlightInfo;
import com.crossover.techtrial.domain.model.FlightSchedule;
import com.crossover.techtrial.domain.model.Plane;
import com.crossover.techtrial.domain.repository.DestinationRepository;
import com.crossover.techtrial.domain.repository.FlightInfoRepository;
import com.crossover.techtrial.domain.repository.PlaneRepository;
import com.crossover.techtrial.domain.service.FlightInfoService;
import com.crossover.techtrial.domain.service.PlaneService;

/**
 * Provides admin API services for managing flight domain data such as 
 * {@link Destination}, {@link FlightInfo}, {@link FlightSchedule} 
 * 
 * @author egunay
 *
 */
@RestController
@RequestMapping("/flightManagement")
public class FlightManagementRestController {

	@Autowired
	DestinationRepository destinationRepository;
	
	@Autowired
	FlightInfoRepository flightInfoRepository;

	@Autowired
	FlightInfoService flightInfoService;
	
	@Autowired
	PlaneRepository planeRepository;
	
	@Autowired
	PlaneService planeService;
	
	/**
	 * returns the collection of all {@link Destination} entities 
	 * 
	 * @return collection of {@link Destination}
	 */
	@RequestMapping(value="/destination/all", method=RequestMethod.GET)
	public Iterable<Destination> findAllDestinations() {
		return destinationRepository.findAll();
	}
	
	/**
	 * upserts a given {@link Destination} entity.  
	 * 
	 * @param destination
	 * @return
	 */
	@RequestMapping(value="/destination/save", method=RequestMethod.POST)
	public Destination saveDestination(@RequestBody Destination destination) {
		destinationRepository.save(destination);
		
		return destination;
	}
	
	@RequestMapping(value="/flightInfo/all", method=RequestMethod.GET)
	public Iterable<FlightInfo> findAllFlightInfoList(
			@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="size", defaultValue="200") Integer size
			) {
		
		PageRequest pageRequest = new PageRequest(page, size);
		
		return flightInfoRepository.findAll(pageRequest);
	}

	@RequestMapping(value="/flightInfo/save", method=RequestMethod.POST)
	public FlightInfo saveFlightInfo(@RequestBody FlightInfo flightInfo) {
		return flightInfoService.save(flightInfo);
	}

	@RequestMapping(value="/plane/all", method=RequestMethod.GET)
	public Iterable<Plane> findAllPlaneList(
			@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="size", defaultValue="200") Integer size
			) {
		
		PageRequest pageRequest = new PageRequest(page, size);
		
		return planeRepository.findAll(pageRequest);
	}
	
	@RequestMapping(value="/plane/addNewPlane", method=RequestMethod.POST)
	public Plane addNewPlane(
			@RequestBody Plane planeTemplate,
			@RequestParam(name="rowCount") Integer rowCount,
			@RequestParam(name="seatGroupCount") Integer seatGroupCount,
			@RequestParam(name="seatCountInAGroup") Integer seatCountInAGroup
			) {
		return planeService.addNewPlane(planeTemplate, rowCount, seatGroupCount, seatCountInAGroup);
	}
}
