package com.crossover.techtrial.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * {@link Entity} class that maps to flight schedule data in database. 
 * 
 * <p>There could be many scheduled flights between two destinations on the same day or different days.
 * Therefore a {@link ManyToOne} relationship is used to refer to {@link FlightInfo}
 * 
 * @author egunay
 *
 */
@Entity
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"flight_info_id", "scheduled_date"})
	}) 
public class FlightSchedule extends AbstractBaseEntity {

	/**
	 * references {@link FlightInfo}
	 */
	@ManyToOne
	@JoinColumn(name="flight_info_id")
	private FlightInfo flightInfo;
	
	/**
	 * scheduled flight date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="scheduled_date")
	private Date scheduledDate;
	
	/**
	 * maximum number of passengers in the flight
	 */
	private Integer maxCapacity;

	public FlightInfo getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(FlightInfo flightInfo) {
		this.flightInfo = flightInfo;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	
	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
}
