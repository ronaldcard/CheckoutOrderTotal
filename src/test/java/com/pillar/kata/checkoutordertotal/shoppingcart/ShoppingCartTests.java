package com.pillar.kata.checkoutordertotal.shoppingcart;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
