package com.crossover.techtrial;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.crossover.techtrial.domain.model.flight.FlightInfo;
import com.crossover.techtrial.domain.model.flight.FlightSchedule;
import com.crossover.techtrial.domain.model.flight.Plane;
import com.crossover.techtrial.domain.model.user.User;
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
@Profile(value = "development")
public class DataLoaderBean {
	
	private static final String PLANE_NAME = "idil";
	private static final String PLANE_MODEL = "BOEING-737-500";
	
	private static final String USER_NAME = "erdem";
	private static final String USER_SURNAME = "gunay";
	private static final String USER_EMAIL = "egunay@gmail.com";

	@Autowired
	PlaneService planeService;
	
	@Autowired
	FlightScheduleService flightScheduleService;
	
	@Autowired
	FlightInfoRepository flightInfoRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostConstruct
	public void initData() {
		FlightInfo flightInfo = flightInfoRepository.findOne(1L);
		
		initUser();
		
		Plane plane = initPlane();
		
		initFlightSchedules(flightInfo, plane);
	}

	private User initUser() {
		User user = new User();
		user.setName(USER_NAME);
		user.setSurname(USER_SURNAME);
		user.setEmail(USER_EMAIL);
		
		userRepository.save(user);
		return user;
	}
	
	private void initFlightSchedules(FlightInfo flightInfo, Plane plane) {
		FlightSchedule flightScheduleTemplate = new FlightSchedule();
		flightScheduleTemplate.setPlane(plane);
		flightScheduleTemplate.setFlightInfo(flightInfo);
		flightScheduleTemplate.setPrice(49.90);
		 
		flightScheduleService.addNewFlightSchedules(flightScheduleTemplate, 20, LocalDateTime.now().plusDays(1));
	}
	
	private Plane initPlane() {
		Plane planeTemplate = new Plane();
		planeTemplate.setName(PLANE_NAME);
		planeTemplate.setModel(PLANE_MODEL);
		
		Plane plane = planeService.addNewPlane(planeTemplate, 20, 2, 3);
		return plane;
	}
}
