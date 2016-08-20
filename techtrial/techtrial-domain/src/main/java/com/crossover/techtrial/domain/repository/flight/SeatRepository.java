package com.crossover.techtrial.domain.repository.flight;

import org.springframework.data.repository.CrudRepository;

import com.crossover.techtrial.domain.model.flight.Plane;
import com.crossover.techtrial.domain.model.flight.Seat;

public interface SeatRepository extends CrudRepository<Seat, Long> {

	public Integer countBySeatNumberAndSeatGroupSeatRowRowNoAndSeatGroupSeatRowPlane(String seatNumber, Integer rowNo, Plane plane);
	
}
