package com.crossover.techtrial.domain;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.crossover.techtrial.domain.model.flight.Destination;
import com.crossover.techtrial.domain.model.flight.FlightInfo;
import com.crossover.techtrial.domain.model.flight.FlightSchedule;
import com.crossover.techtrial.domain.model.flight.Plane;
import com.crossover.techtrial.domain.repository.flight.DestinationRepository;
import com.crossover.techtrial.domain.repository.flight.FlightInfoRepository;
import com.crossover.techtrial.domain.repository.user.UserRepository;
import com.crossover.techtrial.domain.service.flight.FlightScheduleService;
import com.crossover.techtrial.domain.service.flight.PlaneService;

/**
 * this bean loads test data only when the development profile is active
 * 
 * @author egunay
 *
 */
@Component
@Profile(value = {"development", "test"})
public class DataLoaderBean {
	
	private static final String PLANE_NAME = "idil";
	private static final String PLANE_MODEL = "BOEING-737-500";
	
	@Autowired
	PlaneService planeService;
	
	@Autowired
	FlightScheduleService flightScheduleService;
	
	@Autowired
	FlightInfoRepository flightInfoRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DestinationRepository destinationRepository;
	
	@PostConstruct
	public void initData() {
		Destination istanbul = initDestination(Constants.ISTANBUL);
		Destination denizli = initDestination(Constants.DENIZLI);
		
		FlightInfo fromDenizliToIstanbul = initFlightInfo("TK123", denizli, istanbul);
		FlightInfo fromIstanbulToDenizli = initFlightInfo("TK124", istanbul, denizli);
		
		Plane plane1 = initPlane(PLANE_NAME + "-1");
		Plane plane2 = initPlane(PLANE_NAME + "-2");
		
		initFlightSchedules(fromDenizliToIstanbul, plane1);
		initFlightSchedules(fromIstanbulToDenizli, plane2);
	}

	private Destination initDestination(String name) {
		Destination destination = destinationRepository.findByName(name);
		if (destination != null)
			return destination;
		
		destination = new Destination();
		destination.setName(name);
		destinationRepository.save(destination);
		return destination;
	}
	
	public FlightInfo initFlightInfo(String tariffNo, Destination from, Destination to) {
		FlightInfo flightInfo = flightInfoRepository.findByTariffNo(tariffNo);
		if (flightInfo != null)
			return flightInfo;
		
		flightInfo = new FlightInfo();
		flightInfo.setTariffNo(tariffNo);
		flightInfo.setFrom(from);
		flightInfo.setTo(to);
		
		flightInfoRepository.save(flightInfo);
		return flightInfo;
		
	}

	private void initFlightSchedules(FlightInfo flightInfo, Plane plane) {
		FlightSchedule flightScheduleTemplate = new FlightSchedule();
		flightScheduleTemplate.setPlane(plane);
		flightScheduleTemplate.setFlightInfo(flightInfo);
		flightScheduleTemplate.setPrice(49.90);
		 
		flightScheduleService.addNewFlightSchedules(flightScheduleTemplate, 20, LocalDateTime.now().plusDays(1));
	}
	
	private Plane initPlane(String planeName) {
		Plane planeTemplate = new Plane();
		planeTemplate.setName(planeName);
		planeTemplate.setModel(PLANE_MODEL);
		
		Plane plane = planeService.addNewPlane(planeTemplate, 20, 2, 3);
		return plane;
	}
}
