package com.crossover.techtrial.domain.repository.booking;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crossover.techtrial.domain.model.booking.FlightBooking;
import com.crossover.techtrial.domain.model.booking.FlightTicket;
import com.crossover.techtrial.domain.model.flight.FlightSchedule;

public interface FlightTicketRepository extends CrudRepository<FlightTicket, Long> {

	public List<FlightTicket> findAllByFlightBooking(FlightBooking flightBooking);
	
	public List<FlightTicket> findAllByFlightBookingFlightScheduleAndCheckinRowNoAndCheckinSeatNo(FlightSchedule flightSchedule, Integer checkinRowNo, String checkinSeatNo);
	
}
