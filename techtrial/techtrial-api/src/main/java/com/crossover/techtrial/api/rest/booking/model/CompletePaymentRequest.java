package com.crossover.techtrial.api.rest.booking.model;

import com.crossover.techtrial.domain.model.booking.CreditCard;
import com.crossover.techtrial.domain.model.booking.FlightBooking;

/**
 * Composition of {@link CreditCard} and {@link FlightBooking} instances
 * 
 * @author egunay
 *
 */
public class CompletePaymentRequest {

	private CreditCard creditCard;
	
	private FlightBooking flightBooking;

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public FlightBooking getFlightBooking() {
		return flightBooking;
	}

	public void setFlightBooking(FlightBooking flightBooking) {
		this.flightBooking = flightBooking;
	}
	
	
}
