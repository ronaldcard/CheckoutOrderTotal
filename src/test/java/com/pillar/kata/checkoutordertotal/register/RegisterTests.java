package com.pillar.kata.checkoutordertotal.register;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pillar.kata.checkoutordertotal.pricing.Prices;
import com.pillar.kata.checkoutordertotal.pricing.PricesService;

/**
 * Tests for the {@link Register}.
 * 
 * @author tony.card
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Prices.class, PricesService.class, Register.class})
public class RegisterTests {
	
	@Autowired
	private Register register;
	
	/**
	 * Tests that the {@link Prices} was injected properly.
	 */
	@Test
	public void injectionOfPricesService_Test() {
		assertNotNull(this.register);
	}
}
