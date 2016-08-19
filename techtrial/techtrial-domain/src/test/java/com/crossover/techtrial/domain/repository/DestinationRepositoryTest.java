package com.crossover.techtrial.domain.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.techtrial.DomainApplicationMain;
import com.crossover.techtrial.domain.model.Destination;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=DomainApplicationMain.class)
public class DestinationRepositoryTest {

	public static final String NON_EXISTING_DESTINATION = "non_existing";
	
	@Autowired
	DestinationRepository destinationRepository;
	
	@Test
	public void testUniqueConstraint() {
		Destination destination = new Destination();
		destination.setName(Constants.ISTANBUL);
		
		try {
			destinationRepository.save(destination);
			Assert.fail("Should have thrown exception");
		} catch (Exception expected) {
		}
		
	}
	
	
	@Test
	public void testSaveCountDeleteEntity() {
		Destination destination = new Destination();
		destination.setName(NON_EXISTING_DESTINATION);
		
		Assert.assertNull(destination.getId());

		destinationRepository.save(destination);
		Assert.assertNotNull(destination.getId());
		
		long destinationCount = destinationRepository.count();
		Assert.assertTrue(destinationCount > 0);
		
		destinationRepository.delete(destination);
		Assert.assertTrue(destinationRepository.count() == (destinationCount - 1));
	}
	
}
