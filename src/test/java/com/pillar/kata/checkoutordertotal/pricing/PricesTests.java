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
import com.pillar.kata.checkoutordertotal.pricing.special.BuyNGetMAtPercentXOff;
import com.pillar.kata.checkoutordertotal.pricing.special.NforXDollars;
import com.pillar.kata.checkoutordertotal.pricing.special.WeeklySpecial;

/**
 * Tests for the {@link Prices}.
 * 
 * @author tony.card
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Prices.class})
public class PricesTests {
	
	@Autowired
	private Prices prices;
	
	// common test items
	private Item canOfSoup = new Item("can of soup");
	private Item groundBeef = new Item("80% lean ground beef");
	private Item bananas = new Item("bananas");
	
	/**
	 * Tests that the {@link Prices} was injected properly.
	 */
	@Test
	public void injectionOfPricingService_Test() {
		assertNotNull(this.prices);
	}
	
	/**
	 * Tests both setting & getting the base price.
	 */
	@Test
	public void configureBasePriceOfItem_Test() {
		
		final Price canOfSoupPrice = new Price(new BigDecimal("1.89"), Unit.EACH);
		final Price groundBeefPrice = new Price(new BigDecimal("5.99"), Unit.POUND);
		final Price bananasPrice = new Price(new BigDecimal("2.38"), Unit.POUND);
		
		this.prices.setBasePriceOfItem(this.canOfSoup, canOfSoupPrice);
		this.prices.setBasePriceOfItem(this.groundBeef, groundBeefPrice);
		this.prices.setBasePriceOfItem(this.bananas, bananasPrice);
		
		final Price configuredCanOfSoupPrice = this.prices.getBasePriceOfItem(new Item("can of soup"));
		final Price configuredGoundBeefPrice = this.prices.getBasePriceOfItem(new Item("80% lean ground beef"));
		final Price configuredBananasPrice = this.prices.getBasePriceOfItem(new Item("bananas"));
		
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
	
	/**
	 * Tests both setting and getting the markdown price.
	 */
	@Test
	public void configureMarkdowns_Test() {
		
		final Price canOfSoupMarkdown = new Price(new BigDecimal("0.20"), Unit.EACH);
		final Price groundBeefMarkdown = new Price(new BigDecimal("0.50"), Unit.POUND);
		final Price bananasMarkdown = new Price(new BigDecimal("0.15"), Unit.POUND);
		
		this.prices.setItemMarkdown(this.canOfSoup, canOfSoupMarkdown);
		this.prices.setItemMarkdown(this.groundBeef, groundBeefMarkdown);
		this.prices.setItemMarkdown(this.bananas, bananasMarkdown);
		
		final Price configuredCanOfSoupMarkdown = this.prices.getItemMarkdown(new Item("can of soup"));
		final Price configuredGroundBeefMarkdown = this.prices.getItemMarkdown(new Item("80% lean ground beef"));
		final Price configuredBananasMarkdown = this.prices.getItemMarkdown(new Item("bananas"));
		
		// canOfSoup
		assertNotNull(configuredCanOfSoupMarkdown);
		assertEquals(new BigDecimal("0.20"), configuredCanOfSoupMarkdown.getAmount());
		assertEquals(Unit.EACH, configuredCanOfSoupMarkdown.getUnit());
		
		// groundBeef
		assertNotNull(configuredGroundBeefMarkdown);
		assertEquals(new BigDecimal("0.50"), configuredGroundBeefMarkdown.getAmount());
		assertEquals(Unit.POUND, configuredGroundBeefMarkdown.getUnit());
		
		// bananas
		assertNotNull(configuredBananasMarkdown);
		assertEquals(new BigDecimal("0.15"), configuredBananasMarkdown.getAmount());
		assertEquals(Unit.POUND, configuredBananasMarkdown.getUnit());
	}
	
	/**
	 * Tests both setting and getting the weekly specials.
	 */
	@Test
	public void configureWeeklySpecials_Test() {
		
		final WeeklySpecial canOfSoupSpecial = new BuyNGetMAtPercentXOff(1, 1, new BigDecimal("1.00"), 6); // represents 100% => 1 free
		final WeeklySpecial groundBeefSpecial = new BuyNGetMAtPercentXOff(2, 1, new BigDecimal("0.50"), 6); // 50% => half off
		final WeeklySpecial bananasSpecial = new NforXDollars(3, new BigDecimal("5.00"), 6);
		
		this.prices.setItemWeeklySpecial(this.canOfSoup, canOfSoupSpecial);
		this.prices.setItemWeeklySpecial(this.groundBeef, groundBeefSpecial);
		this.prices.setItemWeeklySpecial(this.bananas, bananasSpecial);
		
		final BuyNGetMAtPercentXOff configuredCanOfSoupSpecial = (BuyNGetMAtPercentXOff) this.prices.getItemWeeklySpecial(this.canOfSoup);
		final BuyNGetMAtPercentXOff configuredGroundBeefSpecial = (BuyNGetMAtPercentXOff) this.prices.getItemWeeklySpecial(this.groundBeef);
		final NforXDollars configuredBananasSpecial = (NforXDollars) this.prices.getItemWeeklySpecial(this.bananas);
		
		// canOfSoup
		assertNotNull(configuredCanOfSoupSpecial);
		assertEquals(new Integer(1), configuredCanOfSoupSpecial.getBuyCount());
		assertEquals(new Integer(1), configuredCanOfSoupSpecial.getQuantity());
		assertEquals(new BigDecimal("1.00"), configuredCanOfSoupSpecial.getPercentageOff());
		assertEquals(new Integer(6), configuredCanOfSoupSpecial.getLimit());
		
		// groundBeef
		assertNotNull(configuredGroundBeefSpecial);
		assertEquals(new Integer(2), configuredGroundBeefSpecial.getBuyCount());
		assertEquals(new Integer(1), configuredGroundBeefSpecial.getQuantity());
		assertEquals(new BigDecimal("0.50"), configuredGroundBeefSpecial.getPercentageOff());
		assertEquals(new Integer(6), configuredGroundBeefSpecial.getLimit());
		
		// bananas
		assertNotNull(configuredBananasSpecial);
		assertEquals(new Integer(3), configuredBananasSpecial.getQuantity());
		assertEquals(new BigDecimal("5.00"), configuredBananasSpecial.getAmount());
		assertEquals(new Integer(6), configuredBananasSpecial.getLimit());
	}
}
