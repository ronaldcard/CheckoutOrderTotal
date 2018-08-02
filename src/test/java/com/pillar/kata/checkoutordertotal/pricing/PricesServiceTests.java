package com.pillar.kata.checkoutordertotal.pricing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pillar.kata.checkoutordertotal.item.Item;
import com.pillar.kata.checkoutordertotal.item.Price;
import com.pillar.kata.checkoutordertotal.item.Unit;
import com.pillar.kata.checkoutordertotal.shoppingcart.PurchaseAmount;
import com.pillar.kata.checkoutordertotal.shoppingcart.ShoppingCart;

/**
 * Tests for the {@link PricesService}.
 * 
 * @author tony.card
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Prices.class, PricesService.class})
public class PricesServiceTests {
	
	private static final Item CAN_OF_SOUP = new Item("can of soup");
	private static final Item GROUND_BEEF = new Item("80% lean ground beef");
	private static final Item BANANAS = new Item("bananas");
	
	@Autowired
	private PricesService service;
	
	@Before
	public void setup() {
		
		final Prices testPrices = this.service.getPrices();
		testPrices.setBasePriceOfItem(CAN_OF_SOUP, new Price(new BigDecimal("1.89"), Unit.EACH));
		testPrices.setBasePriceOfItem(GROUND_BEEF, new Price(new BigDecimal("5.99"), Unit.POUND));
		testPrices.setBasePriceOfItem(BANANAS, new Price(new BigDecimal("2.38"), Unit.POUND));
		
	}
	
	/**
	 * Tests that the {@link Prices} was injected properly.
	 */
	@Test
	public void injectionOfPricesService_Test() {
		assertNotNull(this.service);
	}
	
	/**
	 * Tests calculating the base price sub total of an {@link Item}
	 */
	@Test
	public void getBasePriceSubTotalOfItem_Test() {
		
		final ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.addItem(CAN_OF_SOUP, new PurchaseAmount(new BigDecimal("3"), Unit.EACH));
		shoppingCart.addItem(GROUND_BEEF, new PurchaseAmount(new BigDecimal("2"), Unit.POUND));
		shoppingCart.addItem(BANANAS, new PurchaseAmount(new BigDecimal("5"), Unit.POUND));
		
		final BigDecimal canOfSoupBasePriceSubTotal = this.service.getBasePriceSubTotalOfItem(shoppingCart, CAN_OF_SOUP);
		assertEquals(new BigDecimal("5.67"), canOfSoupBasePriceSubTotal);
		
		final BigDecimal groundBeefBasePriceSubTotal = this.service.getBasePriceSubTotalOfItem(shoppingCart, GROUND_BEEF);
		assertEquals(new BigDecimal("11.98"), groundBeefBasePriceSubTotal);
		
		final BigDecimal bananasBasePriceSubTotal = this.service.getBasePriceSubTotalOfItem(shoppingCart, BANANAS);
		assertEquals(new BigDecimal("11.90"), bananasBasePriceSubTotal);
	}
}
