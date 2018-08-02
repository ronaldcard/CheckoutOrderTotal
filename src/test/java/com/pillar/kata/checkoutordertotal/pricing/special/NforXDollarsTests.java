package com.pillar.kata.checkoutordertotal.pricing.special;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
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
import com.pillar.kata.checkoutordertotal.shoppingcart.ShoppingCart;

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
	
	private ShoppingCart shoppingCart;
	private NforXDollars threeFor5;
	private NforXDollars twoFor399;
	
	
	@Before
	public void setup() {
		
		this.shoppingCart = new ShoppingCart();
		this.shoppingCart.addItem(CAN_OF_SOUP, new PurchaseAmount(new BigDecimal("3"), Unit.EACH));
		this.shoppingCart.addItem(GROUND_BEEF, new PurchaseAmount(new BigDecimal("2"), Unit.POUND));
		this.shoppingCart.addItem(BANANAS, new PurchaseAmount(new BigDecimal("5"), Unit.POUND));
		
		this.threeFor5 = new NforXDollars(3, new BigDecimal("5.00"));
		this.twoFor399 = new NforXDollars(2, new BigDecimal("3.99"));
	}
	
	/**
	 * Test the 3 for $5.00 weekly special
	 */
	@Test
	public void threeFor5_Test() {
		
		final PurchaseAmount canOfSoupPurchaseAmount = this.shoppingCart.getItemCount(CAN_OF_SOUP);
		final Price canOfSoupCurrentPrice = new Price(new BigDecimal("1.69"), Unit.EACH);
		
		final PurchaseAmount groundBeefPurchaseAmount = this.shoppingCart.getItemCount(GROUND_BEEF);
		final Price groundBeefCurrentPrice = new Price(new BigDecimal("5.49"), Unit.POUND);
		
		final PurchaseAmount bananasPurchaseAmount = this.shoppingCart.getItemCount(BANANAS);
		final Price bananasCurrentPrice = new Price(new BigDecimal("2.24"), Unit.POUND);
		
		final BigDecimal canOfSoupSubTotal = this.threeFor5.getSubTotal(CAN_OF_SOUP, canOfSoupPurchaseAmount, canOfSoupCurrentPrice);
		final BigDecimal groundBeefSubTotal = this.threeFor5.getSubTotal(GROUND_BEEF, groundBeefPurchaseAmount, groundBeefCurrentPrice);
		final BigDecimal bananasSubTotal = this.threeFor5.getSubTotal(BANANAS, bananasPurchaseAmount, bananasCurrentPrice);
		
		assertEquals(new BigDecimal("5.00"), canOfSoupSubTotal); // 3 cans @ 1.69 -> 1-3 = 5.00 
		assertEquals(new BigDecimal("10.98"), groundBeefSubTotal); // 2 pounds @ 5.49 -> first=5.49, second=5.49
		assertEquals(new BigDecimal("9.48"), bananasSubTotal); // 5 pounds @ 2.24 -> 1-3 = 5.00 + 2 @ 2.24
	}
	
	/**
	 * Tests the 2 for $3.99 weekly special.
	 */
	@Test
	public void twoFor399_Test() {
		
		final PurchaseAmount canOfSoupPurchaseAmount = this.shoppingCart.getItemCount(CAN_OF_SOUP);
		final Price canOfSoupCurrentPrice = new Price(new BigDecimal("1.69"), Unit.EACH);
		
		final PurchaseAmount groundBeefPurchaseAmount = this.shoppingCart.getItemCount(GROUND_BEEF);
		final Price groundBeefCurrentPrice = new Price(new BigDecimal("5.49"), Unit.POUND);
		
		final PurchaseAmount bananasPurchaseAmount = this.shoppingCart.getItemCount(BANANAS);
		final Price bananasCurrentPrice = new Price(new BigDecimal("2.24"), Unit.POUND);
		
		final BigDecimal canOfSoupSubTotal = this.twoFor399.getSubTotal(CAN_OF_SOUP, canOfSoupPurchaseAmount, canOfSoupCurrentPrice);
		final BigDecimal groundBeefSubTotal = this.twoFor399.getSubTotal(GROUND_BEEF, groundBeefPurchaseAmount, groundBeefCurrentPrice);
		final BigDecimal bananasSubTotal = this.twoFor399.getSubTotal(BANANAS, bananasPurchaseAmount, bananasCurrentPrice);
		
		assertEquals(new BigDecimal("5.68"), canOfSoupSubTotal); // 3 cans @ 1.69 -> 1-2 = 3.99 + 1.69 
		assertEquals(new BigDecimal("3.99"), groundBeefSubTotal); // 2 pounds @ 5.49 -> 1-2 = 3.99
		assertEquals(new BigDecimal("10.22"), bananasSubTotal); // 5 pounds @ 2.24 -> 1-2 = 3.99 + 3-4 = 3.99 + 1 @ 2.24
	}
}
