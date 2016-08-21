package com.crossover.techtrial.domain.repository.booking;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crossover.techtrial.domain.model.booking.FlightBooking;
import com.crossover.techtrial.domain.model.user.User;

public interface FlightBookingRepository extends CrudRepository<FlightBooking, Long> {

	/**
	 * find one booking with Id and User that the booking entity belongs to
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	public FlightBooking findByIdAndUser(Long id, User user);
	
	/**
	 * Find all booking entities that belong to given user
	 * 
	 * @param user
	 * @return
	 */
	public List<FlightBooking> findAllByUser(User user);
	
}
