package com.crossover.techtrial.domain.service.flight;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.crossover.techtrial.domain.model.flight.FlightInfo;
import com.crossover.techtrial.domain.model.flight.FlightSchedule;
import com.crossover.techtrial.domain.model.flight.Plane;
import com.crossover.techtrial.domain.repository.flight.FlightInfoRepository;
import com.crossover.techtrial.domain.repository.flight.FlightScheduleRepository;
import com.crossover.techtrial.domain.repository.flight.PlaneRepository;

/**
 * persistence services for {@link FlightSchedule} entity 
 * 
 * @author egunay
 *
 */
@Service
public class FlightScheduleService {
	
	@Autowired
	PlaneRepository planeRepository;
	
	@Autowired
	FlightInfoRepository flightInfoRepository;
	
	@Autowired
	FlightScheduleRepository flightScheduleRepository;
	
	
	/**
	 * adds new {@link FlightSchedule} entities as many as the number of days from given date on
	 * 
	 * @param flightScheduleTemplate
	 * @param numberOfDays
	 * @param startingFrom
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Iterable<FlightSchedule> addNewFlightSchedules(FlightSchedule flightScheduleTemplate, Integer numberOfDays, LocalDateTime startingFrom) {
		List<FlightSchedule> result = new ArrayList<>();
		
		FlightInfo flightInfo = flightInfoRepository.findOne(flightScheduleTemplate.getFlightInfo().getId());
		Assert.notNull(flightInfo);
		
		Plane plane = planeRepository.findOne(flightScheduleTemplate.getPlane().getId());
		Assert.notNull(plane);
		
		plane.setSeatRows(null);
		
		LocalDateTime scheduledDateTime = startingFrom;
		for (int dayNo = 0; dayNo < numberOfDays; dayNo++) {
			FlightSchedule flightSchedule = new FlightSchedule();
			flightSchedule.setFlightInfo(flightInfo);
			flightSchedule.setPlane(plane);
			flightSchedule.setScheduledDate(Date.from(scheduledDateTime.atZone(ZoneId.systemDefault()).toInstant()));
			flightSchedule.setPrice(flightScheduleTemplate.getPrice());
			
			flightScheduleRepository.save(flightSchedule);
			result.add(flightSchedule);
			
			scheduledDateTime = scheduledDateTime.plusDays(1);
		}
		return result;
	}
	
}
