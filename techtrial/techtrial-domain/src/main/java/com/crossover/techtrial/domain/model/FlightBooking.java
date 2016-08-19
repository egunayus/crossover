package com.crossover.techtrial.domain.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class FlightBooking extends AbstractBaseEntity {

	@ManyToOne
	private FlightSchedule flightSchedule;
	
	@ManyToOne
	Passenger passenger;
	
	public FlightSchedule getFlightSchedule() {
		return flightSchedule;
	}
	public void setFlightSchedule(FlightSchedule flightSchedule) {
		this.flightSchedule = flightSchedule;
	}
	
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
}
