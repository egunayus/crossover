package com.crossover.techtrial.domain.service.booking;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.domain.model.booking.CreditCard;
import com.crossover.techtrial.domain.model.booking.FlightBooking;

@Service
public class PaymentService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	public String checkPayment(CreditCard creditCard, FlightBooking flightBooking) {
		log.info("Checking payment amount %d from credit card **** %s ****", flightBooking.getTotalPrice(), creditCard.getNumber().substring(4, 8));
		
		return UUID.randomUUID().toString();
	}
}
