package com.crossover.techtrial.domain.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class FlightBooking extends AbstractBaseEntity {

	@ManyToOne
	private FlightSchedule flightSchedule;
	
	public FlightSchedule getFlightSchedule() {
		return flightSchedule;
	}
	public void setFlightSchedule(FlightSchedule flightSchedule) {
		this.flightSchedule = flightSchedule;
	}
	
}
