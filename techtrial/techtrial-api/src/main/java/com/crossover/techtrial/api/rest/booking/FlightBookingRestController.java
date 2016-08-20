package com.crossover.techtrial.api.rest.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.api.rest.booking.model.CompletePaymentRequest;
import com.crossover.techtrial.api.util.UserUtility;
import com.crossover.techtrial.domain.model.booking.CreditCard;
import com.crossover.techtrial.domain.model.booking.FlightBooking;
import com.crossover.techtrial.domain.model.user.User;
import com.crossover.techtrial.domain.repository.booking.FlightBookingRepository;
import com.crossover.techtrial.domain.service.booking.FlightBookingService;

@RestController
@RequestMapping("/flightBooking")
public class FlightBookingRestController {

	@Autowired
	UserUtility userUtility;
	
	@Autowired
	FlightBookingRepository flightBookingRepository;
	
	@Autowired
	FlightBookingService flightBookingService;
	
	@RequestMapping(value="/userBookings", method=RequestMethod.GET)
	public List<FlightBooking> getUserBookings() {
		User user = userUtility.getCurrentUser();
		
		List<FlightBooking> flightBookingList = flightBookingRepository.findAllByUser(user);
		
		// ignore irrelevant entities
		flightBookingList.stream().forEach(e -> e.getFlightSchedule().setPlane(null));
		
		return flightBookingList;
	}
	
	@RequestMapping(value="/addFlightBooking", method=RequestMethod.POST)
	public FlightBooking addFlightBooking(@RequestBody FlightBooking flightBookingRequest) {
		User user = userUtility.getCurrentUser();
		
		FlightBooking flightBooking = flightBookingService.addNewBooking(flightBookingRequest, user);
		
		// ignore irrelevant entities 
		flightBooking.getFlightSchedule().setPlane(null);
		
		return flightBooking;
	}
	
	@RequestMapping(value="/completePayment", method=RequestMethod.POST)
	public FlightBooking completePayment(@RequestBody CompletePaymentRequest completePaymentRequest) {
		User user = userUtility.getCurrentUser();
		FlightBooking flightBookingRequest = completePaymentRequest.getFlightBooking();
		CreditCard creditCard = completePaymentRequest.getCreditCard();
		
		FlightBooking flightBooking = flightBookingService.checkPayment(flightBookingRequest, creditCard, user);
		
		// ignore irrelevant entities 
		flightBooking.getFlightSchedule().setPlane(null);
		
		return flightBooking;
	}
	
}
