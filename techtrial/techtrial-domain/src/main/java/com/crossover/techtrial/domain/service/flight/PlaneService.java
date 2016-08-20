package com.crossover.techtrial.domain.service.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.techtrial.domain.model.flight.Plane;
import com.crossover.techtrial.domain.model.flight.Seat;
import com.crossover.techtrial.domain.model.flight.SeatGroup;
import com.crossover.techtrial.domain.model.flight.SeatRow;
import com.crossover.techtrial.domain.repository.flight.PlaneRepository;
import com.crossover.techtrial.domain.repository.flight.SeatGroupRepository;
import com.crossover.techtrial.domain.repository.flight.SeatRepository;
import com.crossover.techtrial.domain.repository.flight.SeatRowRepository;

/**
 * persistence services for {@link Plane} entity 
 * 
 * @author egunay
 *
 */
@Service
public class PlaneService {

	@Autowired
	PlaneRepository planeRepository;
	
	@Autowired
	SeatRowRepository seatRowRepository;
	
	@Autowired
	SeatGroupRepository seatGroupRepository;
	
	@Autowired
	SeatRepository seatRepository;

	/**
	 * adds a new {@link Plane} entity and its subordinate seat entities
	 * 
	 * @param model
	 * @param rowCount - the number of rows in the plane
	 * @param seatGroupCount - the number of seat groups in a row e.g. 2, 3
	 * @param seatCountInAGroup - the number of seats in a group e.g. 2, 3
	 * @return persisted {@link Plane}
	 * 
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Plane addNewPlane(Plane planeTemplate, Integer rowCount, Integer seatGroupCount, Integer seatCountInAGroup) {
		Plane plane = new Plane();
		
		plane.setName(planeTemplate.getName());
		plane.setModel(planeTemplate.getModel());
		
		planeRepository.save(plane);
		
		int maxCapacity = 0;
		
		for (int rowNo = 1; rowNo <= rowCount; rowNo++) {
			SeatRow seatRow = new SeatRow();
			seatRow.setRowNo(rowNo);
			seatRow.setPlane(plane);
			
			seatRowRepository.save(seatRow);
			plane.getSeatRows().add(seatRow);
			
			char seatNumber = 'A';
			for (int groupNo = 1; groupNo <= seatGroupCount; groupNo++) {
				SeatGroup seatGroup = new SeatGroup();
				seatGroup.setGroupOrder(groupNo);
				seatGroup.setSeatRow(seatRow);
				
				seatGroupRepository.save(seatGroup);
				seatRow.getSeatGroups().add(seatGroup);
				
				for (int seatNo = 1; seatNo <= seatCountInAGroup; seatNo++) {
					Seat seat = new Seat();
					seat.setSeatNumber(String.valueOf(seatNumber++));
					seat.setSeatGroup(seatGroup);
					
					seatRepository.save(seat);
					seatGroup.getSeats().add(seat);
					maxCapacity++;
				}
			}
		}
		
		plane.setMaxCapacity(maxCapacity);
		planeRepository.save(plane);
		
		return plane;
	}
	
}
