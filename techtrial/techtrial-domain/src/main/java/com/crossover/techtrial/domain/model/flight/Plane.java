package com.crossover.techtrial.domain.model.flight;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.crossover.techtrial.domain.model.AbstractBaseEntity;

@Entity
public class Plane extends AbstractBaseEntity {

	/**
	 * unique nick name of the plane 
	 */
	@Column(length=50, nullable=false, unique=true)
	private String name;
	
	/**
	 * model of the plane e.g. AirBus A 320 or Boeing 737-50
	 */
	private String model; 
	
	/**
	 * maximum number of passengers in the flight
	 */
	private Integer maxCapacity;

	@OneToMany(mappedBy="plane")
	private Set<SeatRow> seatRows = new HashSet<>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Set<SeatRow> getSeatRows() {
		return seatRows;
	}
	public void setSeatRows(Set<SeatRow> seatRows) {
		this.seatRows = seatRows;
	}
	
}
