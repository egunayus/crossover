package com.crossover.techtrial.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class FlightInfo extends AbstractBaseEntity {

	@Column(length=10, unique=true, nullable=false)
	private String tariffNo;
	
	@ManyToOne(optional=false)
	private Destination from;
	
	@ManyToOne(optional=false)
	private Destination to;
	
	public String getTariffNo() {
		return tariffNo;
	}
	public void setTariffNo(String tariffNo) {
		this.tariffNo = tariffNo;
	}
	
	public Destination getFrom() {
		return from;
	}
	public void setFrom(Destination from) {
		this.from = from;
	}
	
	public Destination getTo() {
		return to;
	}
	public void setTo(Destination to) {
		this.to = to;
	}
	
}
