package com.crossover.techtrial.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crossover.techtrial.domain.model.Destination;
import com.crossover.techtrial.domain.model.FlightSchedule;

public interface FlightScheduleRepository extends PagingAndSortingRepository<FlightSchedule, Long> {
	
	@Query(value = "select fs from FlightSchedule fs "
				 + " where fs.flightInfo.from = ?1 "
				 + "   and fs.flightInfo.to = ?2 "
				 + "   and fs.maxCapacity > (select count(*) from FlightBooking b where b.flightSchedule = fs) ")
	public Iterable<FlightSchedule> searchFlights(Destination from, Destination to);
	
}
