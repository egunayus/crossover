package com.crossover.techtrial.domain.booking;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.techtrial.DomainApplicationMain;
import com.crossover.techtrial.domain.Constants;
import com.crossover.techtrial.domain.model.booking.CreditCard;
import com.crossover.techtrial.domain.model.booking.FlightBooking;
import com.crossover.techtrial.domain.model.booking.FlightBookingStatus;
import com.crossover.techtrial.domain.model.booking.FlightTicket;
import com.crossover.techtrial.domain.model.flight.Destination;
import com.crossover.techtrial.domain.model.flight.FlightSchedule;
import com.crossover.techtrial.domain.model.user.Passenger;
import com.crossover.techtrial.domain.model.user.User;
import com.crossover.techtrial.domain.repository.booking.FlightTicketRepository;
import com.crossover.techtrial.domain.repository.flight.DestinationRepository;
import com.crossover.techtrial.domain.repository.flight.FlightScheduleRepository;
import com.crossover.techtrial.domain.repository.user.UserRepository;
import com.crossover.techtrial.domain.service.booking.FlightBookingService;
import com.crossover.techtrial.domain.service.booking.FlightTicketService;

/**
 * end to end flight booking reservation, payment and check in test
 * 
 * @author egunay
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=DomainApplicationMain.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlightBookingTest {

	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	DestinationRepository destinationRepository;
	
	@Autowired
	FlightScheduleRepository flightScheduleRepository;
	
	@Autowired
	FlightBookingService flightBookingService;
	
	@Autowired 
	FlightTicketService flightTicketService;
	
	@Autowired
	FlightTicketRepository flightTicketRepository;
	
	@Test
	public void testFlightBooking() {
		User user = test01User();
		FlightSchedule flightSchedule = test02SearchFlights();
		FlightBooking flightBooking = test03AddNewBooking(flightSchedule, user);
		
		flightBooking = test04CheckPayment(flightBooking, user);
		
		FlightTicket flightTicket = flightTicketRepository.findAllByFlightBooking(flightBooking).get(0);
		Assert.assertNotNull(flightTicket);
		
		flightTicket = test05CheckinTicket(flightTicket, user);
	}
	
	public User test01User() {
		User user = userRepository.findByUsername(Constants.USER_USERNAME);
		if (user == null) {
			user = new User();
			user.setEmail(Constants.USER_EMAIL);
			user.setName(Constants.USER_NAME);
			user.setSurname(Constants.USER_SURNAME);
			user.setUsername(Constants.USER_USERNAME);
			
			userRepository.save(user);
		}
		
		Assert.assertEquals(user.getUsername(), Constants.USER_USERNAME);
		return user;
	}
	
	public FlightSchedule test02SearchFlights() {
		Destination from = destinationRepository.findByName(Constants.ISTANBUL);
		Assert.assertNotNull(from);

		Destination to = destinationRepository.findByName(Constants.DENIZLI);
		Assert.assertNotNull(from);
		
		LocalDateTime ldt = LocalDateTime.now();
		LocalDateTime ldtStart = ldt;
		LocalDateTime ldtEnd = ldt.plusDays(3);
		
		Date startDate = Date.from(ldtStart.atZone(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(ldtEnd.atZone(ZoneId.systemDefault()).toInstant());

		Pageable pageRequest = new PageRequest(0, 20);
		
		Page<FlightSchedule> pageResult = flightScheduleRepository.searchFlights(from, to, startDate, endDate, pageRequest);
		Assert.assertTrue(pageResult.getTotalElements() > 0);
		
		// pick the first flight and assert that it is 
		FlightSchedule flightSchedule = pageResult.getContent().get(0);
		Assert.assertNotNull(flightSchedule);
		
		return flightSchedule;
	}
	
	public FlightBooking test03AddNewBooking(FlightSchedule flightSchedule, User user) {
		FlightBooking flightBooking = new FlightBooking();
		
		flightBooking.setFlightSchedule(flightSchedule);
		flightBooking.setUser(user);
		flightBooking.setNumberOfPassengers(1);
		
		flightBooking = flightBookingService.addNewBooking(flightBooking, user);
		return flightBooking;
	}
	
	public FlightBooking test04CheckPayment(FlightBooking flightBooking, User user) {
		CreditCard creditCard = new CreditCard();
		creditCard.setCardOwner(Constants.USER_NAME);
		creditCard.setNumber("1234-1223-1234-1212");
		creditCard.setCvv2(156);
		creditCard.setExpirationMonth("12");
		creditCard.setExpirationYear(2016);
		
		FlightTicket flightTicket = new FlightTicket();
		Passenger passenger = new Passenger();
		passenger.setName(Constants.USER_NAME);
		passenger.setSurname(Constants.USER_SURNAME);
		passenger.setEmail(Constants.USER_EMAIL);
		flightTicket.setPassenger(passenger);
		
		flightBooking.getTickets().add(flightTicket);
		
		flightBooking = flightBookingService.checkPayment(flightBooking, creditCard, user);
		Assert.assertTrue(flightBooking.getStatus().equals(FlightBookingStatus.PAID));
		
		return flightBooking;
	}
	
	private FlightTicket test05CheckinTicket(FlightTicket flightTicket, User user) {
		flightTicket.setCheckinRowNo(3);
		flightTicket.setCheckinSeatNo("A");
		
		flightTicket = flightTicketService.checkin(flightTicket, user);
		Assert.assertNotNull(flightTicket.getCheckinDate());
		
		return flightTicket;
	}
	
}
