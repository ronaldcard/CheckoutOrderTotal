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
	
	// common test items
	private Item canOfSoup = new Item("can of soup");
	private Item groundBeef = new Item("80% lean ground beef");
	private Item bananas = new Item("bananas");
	
	/**
	 * Tests that the {@link PricingService} was injected properly.
	 */
	@Test
	public void injectionOfPricingService_Test() {
		assertNotNull(this.service);
	}
	
	/**
	 * Tests both setting & getting the base price.
	 */
	@Test
	public void configureBasePriceOfItem_Test() {
		
		final Price canOfSoupPrice = new Price(new BigDecimal("1.89"), Unit.EACH);
		final Price groundBeefPrice = new Price(new BigDecimal("5.99"), Unit.POUND);
		final Price bananasPrice = new Price(new BigDecimal("2.38"), Unit.POUND);
		
		this.service.setBasePriceOfItem(this.canOfSoup, canOfSoupPrice);
		this.service.setBasePriceOfItem(this.groundBeef, groundBeefPrice);
		this.service.setBasePriceOfItem(this.bananas, bananasPrice);
		
		final Price configuredCanOfSoupPrice = this.service.getBasePriceOfItem(new Item("can of soup"));
		final Price configuredGoundBeefPrice = this.service.getBasePriceOfItem(new Item("80% lean ground beef"));
		final Price configuredBananasPrice = this.service.getBasePriceOfItem(new Item("bananas"));
		
		// canOfSoup
		assertNotNull(configuredCanOfSoupPrice);
		assertEquals(new BigDecimal("1.89"), configuredCanOfSoupPrice.getAmount());
		assertEquals(Unit.EACH, configuredCanOfSoupPrice.getUnit());
		
		// groundBeef
		assertNotNull(configuredGoundBeefPrice);
		assertEquals(new BigDecimal("5.99"), configuredGoundBeefPrice.getAmount());
		assertEquals(Unit.POUND, configuredGoundBeefPrice.getUnit());
		
		// bananas
		assertNotNull(configuredBananasPrice);
		assertEquals(new BigDecimal("2.38"), configuredBananasPrice.getAmount());
		assertEquals(Unit.POUND, configuredBananasPrice.getUnit());
	}
}
