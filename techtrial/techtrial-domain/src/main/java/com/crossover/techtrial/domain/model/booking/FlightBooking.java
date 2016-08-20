package com.crossover.techtrial.domain.model.booking;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.crossover.techtrial.domain.model.AbstractBaseEntity;
import com.crossover.techtrial.domain.model.flight.FlightSchedule;
import com.crossover.techtrial.domain.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FlightBooking extends AbstractBaseEntity {

	@ManyToOne
	private FlightSchedule flightSchedule;
	
	@ManyToOne
	private User user;
	
	@Enumerated(EnumType.STRING)
	private FlightBookingStatus status;
	
	/**
	 * number of passengers 
	 */
	private Integer numberOfPassengers;
	
	private Double totalPrice;
	
	@JsonIgnore
	private String paymentTransactionId;
	
	@OneToMany(mappedBy="flightBooking")
	private Set<FlightTicket> tickets = new HashSet<>();
	
	public FlightSchedule getFlightSchedule() {
		return flightSchedule;
	}
	public void setFlightSchedule(FlightSchedule flightSchedule) {
		this.flightSchedule = flightSchedule;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public FlightBookingStatus getStatus() {
		return status;
	}
	public void setStatus(FlightBookingStatus status) {
		this.status = status;
	}
	
	public Integer getNumberOfPassengers() {
		return numberOfPassengers;
	}
	public void setNumberOfPassengers(Integer numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getPaymentTransactionId() {
		return paymentTransactionId;
	}
	public void setPaymentTransactionId(String paymentTransactionId) {
		this.paymentTransactionId = paymentTransactionId;
	}
	
	public Set<FlightTicket> getTickets() {
		return tickets;
	}
	public void setTickets(Set<FlightTicket> tickets) {
		this.tickets = tickets;
	}
	
}
