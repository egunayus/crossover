package com.crossover.techtrial.admin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.domain.model.FlightInfo;
import com.crossover.techtrial.domain.repository.FlightInfoRepository;
import com.crossover.techtrial.domain.service.FlightInfoService;

/**
 * Provides admin API services for managing {@link FlightInfo} domain data 
 * 
 * @author egunay
 *
 */
@RestController
@RequestMapping("/flightInfo")
public class FlightInfoAdminRestController {

	@Autowired
	FlightInfoRepository flightInfoRepository;

	@Autowired
	FlightInfoService flightInfoService;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public Iterable<FlightInfo> findAllFlightInfoList(
			@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="size", defaultValue="200") Integer size
			) {
		
		PageRequest pageRequest = new PageRequest(page, size);
		
		return flightInfoRepository.findAll(pageRequest);
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public FlightInfo saveFlightInfo(@RequestBody FlightInfo flightInfo) {
		return flightInfoService.save(flightInfo);
	}

}
