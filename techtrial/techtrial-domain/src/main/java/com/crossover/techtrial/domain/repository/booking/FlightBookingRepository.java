package com.crossover.techtrial.domain.repository.booking;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crossover.techtrial.domain.model.booking.FlightBooking;
import com.crossover.techtrial.domain.model.user.User;

public interface FlightBookingRepository extends CrudRepository<FlightBooking, Long> {

	public List<FlightBooking> findAllByUser(User user);
	
}
