package com.crossover.techtrial.admin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.domain.model.Plane;
import com.crossover.techtrial.domain.repository.PlaneRepository;
import com.crossover.techtrial.domain.service.PlaneService;

/**
 * Provides admin API services for managing {@link Plane} domain data  
 * 
 * @author egunay
 *
 */
@RestController
@RequestMapping("/plane")
public class PlaneAdminRestController {

	@Autowired
	PlaneRepository planeRepository;
	
	@Autowired
	PlaneService planeService;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public Iterable<Plane> findAllPlaneList(
			@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="size", defaultValue="200") Integer size
			) {
		
		PageRequest pageRequest = new PageRequest(page, size);
		
		return planeRepository.findAll(pageRequest);
	}
	
	@RequestMapping(value="/addNewPlane", method=RequestMethod.POST)
	public Plane addNewPlane(
			@RequestBody Plane planeTemplate,
			@RequestParam(name="rowCount") Integer rowCount,
			@RequestParam(name="seatGroupCount") Integer seatGroupCount,
			@RequestParam(name="seatCountInAGroup") Integer seatCountInAGroup
			) {
		return planeService.addNewPlane(planeTemplate, rowCount, seatGroupCount, seatCountInAGroup);
	}
}
