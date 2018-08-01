package com.pillar.kata.checkoutordertotal.shoppingcart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
	 * Tests adding an item to the shopping cart.
	 */
	@Test
	public void addOneItem_Test() {
		
		final Item canOfSoup = new Item("can of soup");
		
		this.shoppingCart.addItem(canOfSoup, new PurchaseAmount(2.0, Unit.EACH));
		this.shoppingCart.addItem(canOfSoup, new PurchaseAmount(1.0, Unit.EACH));
		
		final PurchaseAmount currentPurchaseAmount = this.shoppingCart.getItemCount(new Item("can of soup"));
		
		assertEquals(3, currentPurchaseAmount.getAmount(), 0);
	}
	
	@Test
	public void addMultipleItem_Test() {
		
		final Item canOfSoup = new Item("can of soup");
		final Item groundBeef = new Item("80% lean ground beef");
		
		this.shoppingCart.addItem(canOfSoup, new PurchaseAmount(2.0, Unit.EACH));
		this.shoppingCart.addItem(canOfSoup, new PurchaseAmount(1.0, Unit.EACH));
		this.shoppingCart.addItem(groundBeef, new PurchaseAmount(1.50, Unit.POUND));
		this.shoppingCart.addItem(groundBeef, new PurchaseAmount(2.78, Unit.POUND));
		
		final PurchaseAmount currentPurchaseAmount = this.shoppingCart.getItemCount(new Item("can of soup"));
		
		assertEquals(3, currentPurchaseAmount.getAmount(), 0); 
	}
}
