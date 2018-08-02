package com.pillar.kata.checkoutordertotal.pricing;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests for the {@link PricesService}.
 * 
 * @author tony.card
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PricesService.class})
public class PricesServiceTests {
	
	@Autowired
	private PricesService service;
	
	/**
	 * Tests that the {@link Prices} was injected properly.
	 */
	@Test
	public void injectionOfPricesService_Test() {
		assertNotNull(this.service);
	}
}
