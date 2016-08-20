package com.crossover.techtrial.domain.model.flight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.crossover.techtrial.domain.model.AbstractBaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Seat extends AbstractBaseEntity {

	/** 
	 * indicates Seat number A, B, C, D, E, F
	 */
	@Column(length=1)
	private String seatNumber;

	/**
	 * references parent {@link SeatGroup} entity
	 */
	@ManyToOne
	@JsonIgnore
	private SeatGroup seatGroup;
	
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	public SeatGroup getSeatGroup() {
		return seatGroup;
	}
	public void setSeatGroup(SeatGroup seatGroup) {
		this.seatGroup = seatGroup;
	}
}
