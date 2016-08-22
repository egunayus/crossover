package com.crossover.techtrial.api.rest.booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.api.rest.booking.model.CompletePaymentRequest;
import com.crossover.techtrial.api.util.MailContentBuilder;
import com.crossover.techtrial.api.util.UserUtility;
import com.crossover.techtrial.domain.model.booking.CreditCard;
import com.crossover.techtrial.domain.model.booking.FlightBooking;
import com.crossover.techtrial.domain.model.user.User;
import com.crossover.techtrial.domain.repository.booking.FlightBookingRepository;
import com.crossover.techtrial.domain.service.booking.FlightBookingService;
import com.crossover.techtrial.domain.service.flight.FlightScheduleService;
import com.crossover.techtrial.domain.service.mail.MailClient;

@RestController
@RequestMapping("/flightBooking")
public class FlightBookingRestController {

	@Autowired
	UserUtility userUtility;
	
	@Autowired
	FlightBookingRepository flightBookingRepository;
	
	@Autowired
	FlightBookingService flightBookingService;

	@Autowired
	FlightScheduleService flightScheduleService;
	
	@Autowired
	MailClient mailClient;
	
	@Autowired
	MailContentBuilder mailContentBuilder;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public FlightBooking findBookingById(@PathVariable("id") Long id) {
		User user = userUtility.getCurrentUser();
		
		return flightBookingRepository.findByIdAndUser(id, user);
	}
	
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
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("message", "hellooo world");
		parameters.put("authToken", userUtility.getAuthToken());
		
		String content = mailContentBuilder.build("mailTemplate", parameters);
		mailClient.prepareAndSend(user.getEmail(), content);
		
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
