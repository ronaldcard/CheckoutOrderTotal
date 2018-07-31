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
		
		final Item canOfSoup = new Item("can of soup");
		final Price canOfSoupPrice = new Price(new BigDecimal("1.89"), Unit.EACH);
		
		final Item groundBeef = new Item("80% lean ground beef");
		final Price groundBeefPrice = new Price(new BigDecimal("5.99"), Unit.POUND);
		
		this.service.setBasePriceOfItem(canOfSoup, canOfSoupPrice);
		this.service.setBasePriceOfItem(groundBeef, groundBeefPrice);
		
		final Price canOfSoupInInventory = this.service.getBasePriceOfItem(new Item("can of soup"));
		final Price goundBeefInInventory = this.service.getBasePriceOfItem(new Item("80% lean ground beef"));
		
		// canOfSoup
		assertNotNull(canOfSoupInInventory);
		assertEquals(new BigDecimal("1.89"), canOfSoupInInventory.getAmount());
		assertEquals(Unit.EACH, canOfSoupInInventory.getUnit());
		
		// groundBeef
		assertNotNull(goundBeefInInventory);
		assertEquals(new BigDecimal("5.99"), goundBeefInInventory.getAmount());
		assertEquals(Unit.POUND, goundBeefInInventory.getUnit());
	}
}
