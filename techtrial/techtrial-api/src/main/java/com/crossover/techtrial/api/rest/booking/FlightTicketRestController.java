package com.crossover.techtrial.api.rest.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.api.util.UserUtility;
import com.crossover.techtrial.domain.model.booking.FlightTicket;
import com.crossover.techtrial.domain.model.user.User;
import com.crossover.techtrial.domain.repository.booking.FlightTicketRepository;
import com.crossover.techtrial.domain.service.booking.FlightTicketService;

@RestController
@RequestMapping("/flightTicket")
public class FlightTicketRestController {

	@Autowired
	UserUtility userUtility;
	
	@Autowired
	FlightTicketRepository flightTicketRepository;
	
	@Autowired
	FlightTicketService flightTicketService;
	
	@RequestMapping(value="/checkin", method=RequestMethod.POST)
	public FlightTicket checkin(@RequestBody FlightTicket flightTicketRequest) {
		User user = userUtility.getCurrentUser();
		
		return flightTicketService.checkin(flightTicketRequest, user);
	}
}
