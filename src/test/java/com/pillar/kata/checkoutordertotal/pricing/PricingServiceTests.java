package com.pillar.kata.checkoutordertotal.pricing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pillar.kata.checkoutordertotal.item.Item;
import com.pillar.kata.checkoutordertotal.item.Price;
import com.pillar.kata.checkoutordertotal.item.Unit;

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
	
	/**
	 * Tests that the {@link PricingService} was injected properly.
	 */
	@Test
	public void injectionOfPricingService_Test() {
		assertNotNull(this.service);
	}
	
	/**
	 * Tests being able to set the base price of an item.
	 */
	@Test
	public void setBasePriceOfItem_Test() {
		
		final Item item = new Item("can of soup");
		final Price priceToSet = new Price(new BigDecimal("1.89"), Unit.EACH);
		
		this.service.setBasePriceOfItem(item, priceToSet);
		
		final Price priceOfItemInInventory = this.service.getBasePriceOfItem(new Item("can of soup"));
		
		assertNotNull(priceOfItemInInventory);
		assertEquals(new BigDecimal("1.89"), priceOfItemInInventory.getAmount());
		assertEquals(Unit.EACH, priceOfItemInInventory.getUnit());
	}
}
