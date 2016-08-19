package com.crossover.techtrial.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.domain.model.Destination;
import com.crossover.techtrial.domain.model.FlightInfo;
import com.crossover.techtrial.domain.repository.DestinationRepository;
import com.crossover.techtrial.domain.repository.FlightInfoRepository;

@Service
public class FlightInfoService {
	
	@Autowired
	DestinationRepository destinationRepository;
	
	@Autowired
	FlightInfoRepository flightInfoRepository;
	

	@Transactional
	public FlightInfo save(FlightInfo flightInfo) {
		Destination from =  destinationRepository.findOne(flightInfo.getFrom().getId());
		Destination to = destinationRepository.findOne(flightInfo.getTo().getId());

		flightInfo.setFrom(from);
		flightInfo.setTo(to);
		
		flightInfoRepository.save(flightInfo);
		
		return flightInfo;
	}
}
