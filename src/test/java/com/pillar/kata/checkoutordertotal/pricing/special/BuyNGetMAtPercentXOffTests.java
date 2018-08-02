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
 * Tests for the {@link BuyNGetMAtPercentXOff}.
 * 
 * @author tony.card
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Prices.class, PricesService.class})
public class BuyNGetMAtPercentXOffTests {
	
	private static final Item CAN_OF_SOUP = new Item("can of soup");
	private static final Item GROUND_BEEF = new Item("80% lean ground beef");
	private static final Item BANANAS = new Item("bananas");
	
	private ShoppingCart shoppingCart;
	private BuyNGetMAtPercentXOff buy1Get1Free;
	private BuyNGetMAtPercentXOff buy2Get1HalfOff;
	
	@Before
	public void setup() {
		
		this.shoppingCart = new ShoppingCart();
		this.shoppingCart.addItem(CAN_OF_SOUP, new PurchaseAmount(new BigDecimal("3"), Unit.EACH));
		this.shoppingCart.addItem(GROUND_BEEF, new PurchaseAmount(new BigDecimal("2"), Unit.POUND));
		this.shoppingCart.addItem(BANANAS, new PurchaseAmount(new BigDecimal("5"), Unit.POUND));
		
		this.buy1Get1Free = new BuyNGetMAtPercentXOff(1, 1, new BigDecimal("1"));
		this.buy2Get1HalfOff = new BuyNGetMAtPercentXOff(2, 1, new BigDecimal("0.50"));
	}
	
	/**
	 * Tests a buy 1 get 1 free weekly special.
	 */
	@Test
	public void buyOneGetOneFreeSubTotal_Test() {
		
		final PurchaseAmount canOfSoupPurchaseAmount = this.shoppingCart.getItemCount(CAN_OF_SOUP);
		final Price canOfSoupCurrentPrice = new Price(new BigDecimal("1.69"), Unit.EACH);
		
		final PurchaseAmount groundBeefPurchaseAmount = this.shoppingCart.getItemCount(GROUND_BEEF);
		final Price groundBeefCurrentPrice = new Price(new BigDecimal("5.49"), Unit.POUND);
		
		final PurchaseAmount bananasPurchaseAmount = this.shoppingCart.getItemCount(BANANAS);
		final Price bananasCurrentPrice = new Price(new BigDecimal("2.24"), Unit.POUND);
		
		final BigDecimal canOfSoupSubTotal = this.buy1Get1Free.getSubTotal(CAN_OF_SOUP, canOfSoupPurchaseAmount, canOfSoupCurrentPrice);
		final BigDecimal groundBeefSubTotal = this.buy1Get1Free.getSubTotal(GROUND_BEEF, groundBeefPurchaseAmount, groundBeefCurrentPrice);
		final BigDecimal bananasSubTotal = this.buy1Get1Free.getSubTotal(BANANAS, bananasPurchaseAmount, bananasCurrentPrice);
		
		assertEquals(new BigDecimal("3.38"), canOfSoupSubTotal); // 3 cans @ 1.69 -> first=1.69, second=free, third=1.69
		assertEquals(new BigDecimal("5.49"), groundBeefSubTotal); // 2 pounds @ 5.49 -> first=5.49, second=free
		assertEquals(new BigDecimal("6.72"), bananasSubTotal); // 5 pounds @ 2.24 -> first=2.24, second=free, third=2.24, fourth=free, fifth=2.24
	}
	
	/**
	 * Tests a buy 2 get 1 50% off weekly special.
	 */
	@Test
	public void buy2Get1HalfOff_Test() {
		
		final PurchaseAmount canOfSoupPurchaseAmount = this.shoppingCart.getItemCount(CAN_OF_SOUP);
		final Price canOfSoupCurrentPrice = new Price(new BigDecimal("1.69"), Unit.EACH);
		
		final PurchaseAmount groundBeefPurchaseAmount = this.shoppingCart.getItemCount(GROUND_BEEF);
		final Price groundBeefCurrentPrice = new Price(new BigDecimal("5.49"), Unit.POUND);
		
		final PurchaseAmount bananasPurchaseAmount = this.shoppingCart.getItemCount(BANANAS);
		final Price bananasCurrentPrice = new Price(new BigDecimal("2.24"), Unit.POUND);
		
		final BigDecimal canOfSoupSubTotal = this.buy2Get1HalfOff.getSubTotal(CAN_OF_SOUP, canOfSoupPurchaseAmount, canOfSoupCurrentPrice);
		final BigDecimal groundBeefSubTotal = this.buy2Get1HalfOff.getSubTotal(GROUND_BEEF, groundBeefPurchaseAmount, groundBeefCurrentPrice);
		final BigDecimal bananasSubTotal = this.buy2Get1HalfOff.getSubTotal(BANANAS, bananasPurchaseAmount, bananasCurrentPrice);
		
		assertEquals(new BigDecimal("4.23"), canOfSoupSubTotal); // 3 cans @ 1.69 -> first=1.69, second=1.69, third=1.69
		assertEquals(new BigDecimal("10.98"), groundBeefSubTotal); // 2 pounds @ 5.49 -> first=5.49, second=5.49 
		assertEquals(new BigDecimal("10.08"), bananasSubTotal); // 5 pounds @ 2.24 -> first=2.24, second=2.24, third=1.12, fourth=2.24, fifth=2.24
	}
}
