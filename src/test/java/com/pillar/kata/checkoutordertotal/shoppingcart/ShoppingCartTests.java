package com.pillar.kata.checkoutordertotal.shoppingcart;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pillar.kata.checkoutordertotal.item.Item;
import com.pillar.kata.checkoutordertotal.item.Unit;

/**
 * Tests for the ShoppingCart.
 * 
 * @author tony.card
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShoppingCart.class})
public class ShoppingCartTests {
	
	private ShoppingCart shoppingCart;
	
	@Before
	public void setup() {
		
		this.shoppingCart = new ShoppingCart();
	}
	
	@Test
	public void initializeShoppingCart_Test() {
		
		assertNotNull(this.shoppingCart);
	}
	
	/**
	 * Tests adding an item to the shopping cart.  Confirms the amount in the shopping cart.
	 */
	@Test
	public void addOneItem_Test() {
		
		final Item canOfSoup = new Item("can of soup");
		
		this.shoppingCart.addItem(canOfSoup, new PurchaseAmount(new BigDecimal("2"), Unit.EACH));
		this.shoppingCart.addItem(canOfSoup, new PurchaseAmount(new BigDecimal("1"), Unit.EACH));
		
		final PurchaseAmount currentPurchaseAmount = this.shoppingCart.getItemCount(new Item("can of soup"));
		
		assertEquals(new BigDecimal("3"), currentPurchaseAmount.getAmount());
		assertEquals(new PurchaseAmount(new BigDecimal("3"), Unit.EACH), currentPurchaseAmount);
	}
	
	/**
	 * Tests adding multiple type of items to the shopping cart.  Confirms the amounts in the shopping carts.
	 */
	@Test
	public void addMultipleItem_Test() {
		
		final Item canOfSoup = new Item("can of soup");
		final Item groundBeef = new Item("80% lean ground beef");
		
		this.shoppingCart.addItem(canOfSoup, new PurchaseAmount(new BigDecimal("2"), Unit.EACH));
		this.shoppingCart.addItem(canOfSoup, new PurchaseAmount(new BigDecimal("1"), Unit.EACH));
		this.shoppingCart.addItem(groundBeef, new PurchaseAmount(new BigDecimal("1.50"), Unit.POUND));
		this.shoppingCart.addItem(groundBeef, new PurchaseAmount(new BigDecimal("2.78"), Unit.POUND));
		
		final PurchaseAmount canOfSoupCurrentPurchaseAmount = this.shoppingCart.getItemCount(new Item("can of soup"));
		final PurchaseAmount groundBeefCurrentPurchaseAmount = this.shoppingCart.getItemCount(new Item("80% lean ground beef"));
		
		assertEquals(new BigDecimal("3"), canOfSoupCurrentPurchaseAmount.getAmount());
		assertEquals(new PurchaseAmount(new BigDecimal("3"), Unit.EACH), canOfSoupCurrentPurchaseAmount);
		assertEquals(new PurchaseAmount(new BigDecimal("4.28"), Unit.POUND), groundBeefCurrentPurchaseAmount);
	}
	
	/**
	 * Tests removing an item from the shopping cart.  Confirms the amount in the shopping cart.
	 */
	@Test
	public void removeAnItem_Test() {
		
		final Item canOfSoup = new Item("can of soup");
		
		// start with 2
		this.shoppingCart.addItem(canOfSoup, new PurchaseAmount(new BigDecimal("2"), Unit.EACH));
		final PurchaseAmount currentPurchaseAmount = this.shoppingCart.getItemCount(new Item("can of soup"));
		
		// confirm 2 in the shopping cart
		assertEquals(new BigDecimal("2"), currentPurchaseAmount.getAmount());
		
		// remove an item
		this.shoppingCart.removeItem(canOfSoup, new PurchaseAmount(new BigDecimal("1"), Unit.EACH));
		
		// confirm the updated purchase amount
		assertEquals(new BigDecimal("1"), currentPurchaseAmount.getAmount());
	}
}
