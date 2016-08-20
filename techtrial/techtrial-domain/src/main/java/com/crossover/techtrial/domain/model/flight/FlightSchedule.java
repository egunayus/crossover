package com.crossover.techtrial.domain.model.flight;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.crossover.techtrial.domain.model.AbstractBaseEntity;

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
	 * references {@link Plane} entity
	 */
	@ManyToOne
	Plane plane;
	
	/**
	 * standart fare price
	 */
	@Column(nullable=false)
	private Double price;
	
	public FlightInfo getFlightInfo() {
		return flightInfo;
	}
	public void setFlightInfo(FlightInfo flightInfo) {
		this.flightInfo = flightInfo;
	}

	public Plane getPlane() {
		return plane;
	}
	public void setPlane(Plane plane) {
		this.plane = plane;
	}
	
	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}