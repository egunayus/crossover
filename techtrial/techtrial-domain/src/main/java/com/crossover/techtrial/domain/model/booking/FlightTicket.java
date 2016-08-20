package com.crossover.techtrial.domain.model.booking;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.crossover.techtrial.domain.model.AbstractBaseEntity;
import com.crossover.techtrial.domain.model.user.Passenger;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FlightTicket extends AbstractBaseEntity {

	@ManyToOne
	private Passenger passenger;
	
	@ManyToOne
	@JsonIgnore
	private FlightBooking flightBooking;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkinDate;
	
	private Integer checkinRowNo;
	
	private String checkinSeatNo; 
	
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	public FlightBooking getFlightBooking() {
		return flightBooking;
	}
	public void setFlightBooking(FlightBooking flightBooking) {
		this.flightBooking = flightBooking;
	}
	
	public Date getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}
	
	public Integer getCheckinRowNo() {
		return checkinRowNo;
	}
	public void setCheckinRowNo(Integer checkinRowNo) {
		this.checkinRowNo = checkinRowNo;
	}
	public String getCheckinSeatNo() {
		return checkinSeatNo;
	}
	
	public void setCheckinSeatNo(String checkinSeatNo) {
		this.checkinSeatNo = checkinSeatNo;
	}
	
	
}
