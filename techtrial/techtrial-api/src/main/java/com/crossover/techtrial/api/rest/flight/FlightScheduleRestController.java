package com.crossover.techtrial.api.rest.flight;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.api.util.UserUtility;
import com.crossover.techtrial.domain.model.booking.FlightBooking;
import com.crossover.techtrial.domain.model.flight.Destination;
import com.crossover.techtrial.domain.model.flight.FlightSchedule;
import com.crossover.techtrial.domain.model.user.User;
import com.crossover.techtrial.domain.repository.booking.FlightBookingRepository;
import com.crossover.techtrial.domain.repository.flight.DestinationRepository;
import com.crossover.techtrial.domain.repository.flight.FlightScheduleRepository;

@RestController
@RequestMapping("/flightSchedule")
public class FlightScheduleRestController {

	@Autowired
	UserUtility userUtility;
	
	@Autowired
	FlightScheduleRepository flightScheduleRepository;
	
	@Autowired
	DestinationRepository destinationRepository;
	
	@Autowired
	FlightBookingRepository flightBookingRepository;
	
	
	/**
	 * Searches for {@link FlightSchedule} entities
	 * 
	 * @param from
	 * @param to
	 * @param tripDate
	 * @param returnTripDate
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public List<FlightSchedule> searchFlights(
			@RequestParam("from") Long from,
			@RequestParam("to") Long to,
			@RequestParam(name="tripDate", required=true) String tripDate,
			@RequestParam(name="flexDaysCount", defaultValue="0") Integer flexDaysCount
			) {
		
		LocalDateTime tripDateTime = LocalDateTime.parse(tripDate);
		tripDateTime = tripDateTime.truncatedTo(ChronoUnit.DAYS);
		
		Date startDate = Date.from(tripDateTime.minusDays(flexDaysCount).atZone(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(tripDateTime.plusDays(1 + flexDaysCount).atZone(ZoneId.systemDefault()).toInstant());
		
		Destination fromDestination = destinationRepository.findOne(from);
		Destination toDestination = destinationRepository.findOne(to);
		
		Assert.notNull(fromDestination);
		Assert.notNull(toDestination);
		
		List<FlightSchedule> result =  flightScheduleRepository.searchFlights(fromDestination, toDestination, startDate, endDate);
		
		// set plane seat rows null so that the seat model is not necessarily fetched.
		result.stream().forEach(e -> e.getPlane().setSeatRows(null));
		
		return result;
	}
	
	@RequestMapping(value="/findByFlightBooking/{flightBookingId}", method=RequestMethod.GET)
	public FlightSchedule findByFlightBooking(@PathVariable("flightBookingId") Long flightBookingId) {
		User user = userUtility.getCurrentUser();

		FlightBooking flightBooking = flightBookingRepository.findOne(flightBookingId);
		Assert.notNull(flightBooking);
		
		Assert.isTrue(flightBooking.getUser().equals(user));
		
		return flightBooking.getFlightSchedule();
	}
}
