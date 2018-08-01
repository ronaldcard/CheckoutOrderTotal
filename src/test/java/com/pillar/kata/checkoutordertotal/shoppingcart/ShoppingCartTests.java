package com.pillar.kata.checkoutordertotal.shoppingcart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pillar.kata.checkoutordertotal.item.Item;

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
	public void addItem_Test() {
		
		final Item canOfSoup = new Item("can of soup");
		
		this.shoppingCart.addItem(canOfSoup, 2);
		this.shoppingCart.addItem(canOfSoup, 1);
		
		assertEquals(3, this.shoppingCart.getItemCount(new Item("can of soup")));
	}
}
