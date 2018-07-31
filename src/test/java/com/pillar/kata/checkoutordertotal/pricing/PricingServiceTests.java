package com.pillar.kata.checkoutordertotal.pricing;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests for the {@link PricingService}.
 * 
 * @author PatientZero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PricingService.class})
public class PricingServiceTests {
	
	@Autowired
	private PricingService service;
	
	@Test
	public void injectionOfPricingService_Test() {
		assertNotNull(this.service);
	}
}
