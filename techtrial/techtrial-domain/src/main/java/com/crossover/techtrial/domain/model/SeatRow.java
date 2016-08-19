package com.crossover.techtrial.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SeatRow extends AbstractBaseEntity {

	/**
	 * indicates the row number from head to tail. e.g. 1, 2, 3, etc.
	 */
	private Integer rowNo;

	/**
	 * references parent {@link Plane} entity
	 */
	@ManyToOne
	@JsonIgnore
	Plane plane;
	
	/**
	 * set of individual seats in the group.
	 */
	@OneToMany(mappedBy="seatRow")
	private Set<SeatGroup> seatGroups = new HashSet<>();

	public Integer getRowNo() {
		return rowNo;
	}
	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}

	public Plane getPlane() {
		return plane;
	}
	public void setPlane(Plane plane) {
		this.plane = plane;
	}
	
	public Set<SeatGroup> getSeatGroups() {
		return seatGroups;
	}
	public void setSeatGroups(Set<SeatGroup> seatGroups) {
		this.seatGroups = seatGroups;
	}

	
	
}
