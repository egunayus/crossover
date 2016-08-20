package com.crossover.techtrial.domain.service.booking;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.crossover.techtrial.domain.model.booking.CreditCard;
import com.crossover.techtrial.domain.model.booking.FlightBooking;
import com.crossover.techtrial.domain.model.booking.FlightBookingStatus;
import com.crossover.techtrial.domain.model.booking.FlightTicket;
import com.crossover.techtrial.domain.model.flight.FlightSchedule;
import com.crossover.techtrial.domain.model.user.Passenger;
import com.crossover.techtrial.domain.model.user.User;
import com.crossover.techtrial.domain.repository.booking.FlightBookingRepository;
import com.crossover.techtrial.domain.repository.booking.FlightTicketRepository;
import com.crossover.techtrial.domain.repository.flight.FlightScheduleRepository;
import com.crossover.techtrial.domain.repository.user.PassengerRepository;

@Service
public class FlightBookingService {
	
	@Autowired
	FlightScheduleRepository flightScheduleRepository;
	
	@Autowired
	FlightBookingRepository flightBookingRepository;
	
	@Autowired
	FlightTicketRepository flightTicketRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	PaymentService paymentService;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public FlightBooking addNewBooking(FlightBooking flightBookingRequest, User user) {
		FlightSchedule flightSchedule = flightScheduleRepository.findOne(flightBookingRequest.getFlightSchedule().getId());
		Assert.notNull(flightSchedule);
		
		FlightBooking flightBooking = new FlightBooking();
		flightBooking.setFlightSchedule(flightSchedule);
		flightBooking.setUser(user);
		flightBooking.setStatus(FlightBookingStatus.RESERVED);
		flightBooking.setNumberOfPassengers(flightBookingRequest.getNumberOfPassengers());
		
		double totalPrice = flightSchedule.getPrice() * flightBookingRequest.getNumberOfPassengers();
		flightBooking.setTotalPrice(totalPrice);
		
		flightBookingRepository.save(flightBooking);
		
		return flightBooking;
	}
	
	@Transactional
	public FlightBooking checkPayment(FlightBooking flightBookingRequest, CreditCard creditCard, User user) {
		FlightBooking flightBooking = flightBookingRepository.findOne(flightBookingRequest.getId());
		Assert.notNull(flightBooking);
		
		// check if the current user is the owner of the booking
		Assert.isTrue(flightBooking.getUser().equals(user));
		
		// check passenger entities
		Assert.notEmpty(flightBookingRequest.getTickets());
		Assert.isTrue(flightBookingRequest.getTickets().size() == flightBooking.getNumberOfPassengers());
		
		for (FlightTicket flightTicket : flightBookingRequest.getTickets()) {
			Assert.notNull(flightTicket.getPassenger());
			
			if (flightTicket.getPassenger().getId() != null) {
				Passenger passenger = passengerRepository.findOne(flightTicket.getPassenger().getId());

				// make sure that passenger exists
				Assert.notNull(passenger);
				
				// make sure that passenger entity belongs to current user
				Assert.isTrue(passenger.getUser().equals(user));
				
				flightTicket.setPassenger(passenger);
			} else {
				flightTicket.getPassenger().setUser(user);
			}
		}

		// check payment from credit card
		String transactionId = paymentService.checkPayment(creditCard, flightBooking);
		
		flightBooking.setPaymentTransactionId(transactionId);
		flightBooking.setStatus(FlightBookingStatus.PAID);
		flightBookingRepository.save(flightBooking);
		
		// create flight tickets 
		for (FlightTicket flightTicketRequest : flightBookingRequest.getTickets()) {
			Passenger passenger = flightTicketRequest.getPassenger();
			FlightTicket flightTicket = new FlightTicket();
			flightTicket.setPassenger(passenger);
			flightTicket.setFlightBooking(flightBooking);
			
			// save passenger if not persisted before
			if (passenger.getId() == null) 
				passengerRepository.save(passenger);
			
			flightTicketRepository.save(flightTicket);
		}
		
		return flightBooking;
	}
}
