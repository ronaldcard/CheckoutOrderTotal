package com.pillar.kata.checkoutordertotal.pricing.special;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pillar.kata.checkoutordertotal.item.Item;
import com.pillar.kata.checkoutordertotal.item.Price;
import com.pillar.kata.checkoutordertotal.item.Unit;
import com.pillar.kata.checkoutordertotal.pricing.Prices;
import com.pillar.kata.checkoutordertotal.pricing.PricesService;
import com.pillar.kata.checkoutordertotal.shoppingcart.PurchaseAmount;

/**
 * .
 * 
 * @author PatientZero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Prices.class, PricesService.class})
public class NforXDollarsTests {
	
	private static final Item CAN_OF_SOUP = new Item("can of soup");
	private static final Item GROUND_BEEF = new Item("80% lean ground beef");
	private static final Item BANANAS = new Item("bananas");
	
	private static final Price CAN_OF_SOUP_PRICE = new Price(new BigDecimal("1.69"), Unit.EACH);
	private static final Price GROUND_BEEF_PRICE = new Price(new BigDecimal("5.49"), Unit.POUND);
	private static final Price BANANAS_PRICE = new Price(new BigDecimal("2.24"), Unit.POUND);
	
	private static final PurchaseAmount CAN_OF_SOUP_PURCHASE_AMOUNT = new PurchaseAmount(new BigDecimal("3"), Unit.EACH);
	private static final PurchaseAmount GROUND_BEEF_PURCHASE_AMOUNT = new PurchaseAmount(new BigDecimal("2"), Unit.POUND);
	private static final PurchaseAmount BANANAS_PURCHASE_AMOUNT = new PurchaseAmount(new BigDecimal("5"), Unit.POUND);
	
	
	/**
	 * Test the 3 for $5.00 weekly special
	 */
	@Test
	public void threeFor5_Test() {
		
		final NforXDollars threeFor5 = new NforXDollars(3, new BigDecimal("5.00"));
		
		final BigDecimal canOfSoupSubTotal = threeFor5.getSubTotal(CAN_OF_SOUP, CAN_OF_SOUP_PURCHASE_AMOUNT, CAN_OF_SOUP_PRICE);
		final BigDecimal groundBeefSubTotal = threeFor5.getSubTotal(GROUND_BEEF, GROUND_BEEF_PURCHASE_AMOUNT, GROUND_BEEF_PRICE);
		final BigDecimal bananasSubTotal = threeFor5.getSubTotal(BANANAS, BANANAS_PURCHASE_AMOUNT, BANANAS_PRICE);
		
		assertEquals(new BigDecimal("5.00"), canOfSoupSubTotal); // 3 cans @ 1.69 -> 1-3 = 5.00 
		assertEquals(new BigDecimal("10.98"), groundBeefSubTotal); // 2 pounds @ 5.49 -> first=5.49, second=5.49
		assertEquals(new BigDecimal("9.48"), bananasSubTotal); // 5 pounds @ 2.24 -> 1-3 = 5.00 + 2 @ 2.24
	}
	
	/**
	 * Tests the 2 for $3.99 weekly special.
	 */
	@Test
	public void twoFor399_Test() {
		
		final NforXDollars twoFor399 = new NforXDollars(2, new BigDecimal("3.99"));
		
		final BigDecimal canOfSoupSubTotal = twoFor399.getSubTotal(CAN_OF_SOUP, CAN_OF_SOUP_PURCHASE_AMOUNT, CAN_OF_SOUP_PRICE);
		final BigDecimal groundBeefSubTotal = twoFor399.getSubTotal(GROUND_BEEF, GROUND_BEEF_PURCHASE_AMOUNT, GROUND_BEEF_PRICE);
		final BigDecimal bananasSubTotal = twoFor399.getSubTotal(BANANAS, BANANAS_PURCHASE_AMOUNT, BANANAS_PRICE);
		
		assertEquals(new BigDecimal("5.68"), canOfSoupSubTotal); // 3 cans @ 1.69 -> 1-2 = 3.99 + 1.69 
		assertEquals(new BigDecimal("3.99"), groundBeefSubTotal); // 2 pounds @ 5.49 -> 1-2 = 3.99
		assertEquals(new BigDecimal("10.22"), bananasSubTotal); // 5 pounds @ 2.24 -> 1-2 = 3.99 + 3-4 = 3.99 + 1 @ 2.24
	}
}
