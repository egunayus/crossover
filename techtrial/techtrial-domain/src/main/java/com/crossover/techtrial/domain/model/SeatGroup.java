package com.crossover.techtrial.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SeatGroup extends AbstractBaseEntity {

	/**
	 * indicates the order of seat groups from left to right. e.g. 1 - left, 2 - middle, 3 - right
	 */
	private Integer groupOrder;
	
	/**
	 * references parent {@link SeatRow} entity
	 */
	@ManyToOne
	@JsonIgnore
	private SeatRow seatRow;
	
	/**
	 * set of individual seats in the group.
	 */
	@OneToMany(mappedBy="seatGroup")
	private Set<Seat> seats = new HashSet<>();

	public Integer getGroupOrder() {
		return groupOrder;
	}
	public void setGroupOrder(Integer groupOrder) {
		this.groupOrder = groupOrder;
	}
	
	public SeatRow getSeatRow() {
		return seatRow;
	}
	public void setSeatRow(SeatRow seatRow) {
		this.seatRow = seatRow;
	}
	
	public Set<Seat> getSeats() {
		return seats;
	}
	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}
	
	
}
