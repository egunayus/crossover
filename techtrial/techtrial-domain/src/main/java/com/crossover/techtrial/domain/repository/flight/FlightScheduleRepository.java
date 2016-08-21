package com.crossover.techtrial.domain.repository.flight;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crossover.techtrial.domain.model.flight.Destination;
import com.crossover.techtrial.domain.model.flight.FlightSchedule;

public interface FlightScheduleRepository extends PagingAndSortingRepository<FlightSchedule, Long> {
	
	@Query(value = "select fs from FlightSchedule fs "
				 + " where fs.flightInfo.from = ?1 "
				 + "   and fs.flightInfo.to = ?2 "
				 + "   and fs.plane.maxCapacity > (select count(*) from FlightTicket t where t.flightBooking.flightSchedule = fs) "
				 + "   and fs.scheduledDate between ?3 and ?4"
		)
	public Page<FlightSchedule> searchFlights(Destination from, Destination to, Date startDate, Date endDate, Pageable pageRequest);
	
}
