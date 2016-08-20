package com.crossover.techtrial.domain.service.booking;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.crossover.techtrial.domain.model.booking.FlightBookingStatus;
import com.crossover.techtrial.domain.model.booking.FlightTicket;
import com.crossover.techtrial.domain.model.flight.FlightSchedule;
import com.crossover.techtrial.domain.model.flight.Plane;
import com.crossover.techtrial.domain.model.user.User;
import com.crossover.techtrial.domain.repository.booking.FlightTicketRepository;
import com.crossover.techtrial.domain.repository.flight.SeatRepository;

@Service
public class FlightTicketService {

	@Autowired
	FlightTicketRepository flightTicketRepository;
	
	@Autowired
	SeatRepository seatRepository;
	
	public FlightTicket checkin(FlightTicket flightTicketRequest, User user) {
		FlightTicket flightTicket = flightTicketRepository.findOne(flightTicketRequest.getId());
		Assert.notNull(flightTicket, "No ticket with id : " + flightTicketRequest.getId());
		
		// check if the current user owns the booking 
		Assert.isTrue(flightTicket.getFlightBooking().getUser().equals(user), "Play with your own entities");
		
		// check if this ticket is already checked in
		Assert.isNull(flightTicket.getCheckinDate(), "Already checked in");
		
		// check if the payment is done
		boolean isPaymentOk = flightTicket.getFlightBooking().getStatus() == FlightBookingStatus.PAID;
		Assert.isTrue(isPaymentOk, "Payment first please!!!");
		
		Integer checkinRowNo = flightTicketRequest.getCheckinRowNo();
		String checkinSeatNo = flightTicketRequest.getCheckinSeatNo();
		
		Assert.notNull(checkinRowNo);
		Assert.hasText(checkinSeatNo);

		FlightSchedule flightSchedule = flightTicket.getFlightBooking().getFlightSchedule();
		
		// check if the specified Seat exists on scheduled flight
		Plane plane = flightSchedule.getPlane();
		int count = seatRepository.countBySeatNumberAndSeatGroupSeatRowRowNoAndSeatGroupSeatRowPlane(checkinSeatNo, checkinRowNo, plane);
		Assert.isTrue(count == 1, "There is no such seat : " + checkinRowNo + "-" + checkinSeatNo);
		
		List<FlightTicket> ticketList = flightTicketRepository.findAllByFlightBookingFlightScheduleAndCheckinRowNoAndCheckinSeatNo(flightSchedule, checkinRowNo, checkinSeatNo);
		Assert.isTrue(ticketList.size() == 0, "The seat is already taken : " + checkinRowNo + "-" + checkinSeatNo);
		
		// do check in
		flightTicket.setCheckinDate(new Date(System.currentTimeMillis()));
		flightTicket.setCheckinRowNo(checkinRowNo);
		flightTicket.setCheckinSeatNo(checkinSeatNo);
		flightTicketRepository.save(flightTicket);
		
		return flightTicket;
	}
}
