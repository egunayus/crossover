package com.crossover.techtrial.domain.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.crossover.techtrial.DomainApplicationMain;
import com.crossover.techtrial.domain.Constants;
import com.crossover.techtrial.domain.model.flight.Destination;
import com.crossover.techtrial.domain.model.flight.FlightInfo;
import com.crossover.techtrial.domain.repository.flight.DestinationRepository;
import com.crossover.techtrial.domain.repository.flight.FlightInfoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=DomainApplicationMain.class)
public class FlightInfoRepositoryTest {
	
	private static final String NON_EXISTING_TARIFF_NO = "xx_123";

	@Autowired
	FlightInfoRepository flightInfoRepository;
	
	@Autowired
	DestinationRepository destinationRepository;
	
	@Test
	public void testSaveNewEntity() {
		Destination from = destinationRepository.findByName(Constants.ISTANBUL);
		Assert.notNull(from);
		
		Destination to = destinationRepository.findByName(Constants.DENIZLI);
		Assert.notNull(to);
				
		FlightInfo flightInfo = new FlightInfo();
		flightInfo.setTariffNo(NON_EXISTING_TARIFF_NO);
		flightInfo.setFrom(from);
		flightInfo.setTo(to);
		
		flightInfoRepository.save(flightInfo);
		Assert.notNull(flightInfo.getId());
	}
}
