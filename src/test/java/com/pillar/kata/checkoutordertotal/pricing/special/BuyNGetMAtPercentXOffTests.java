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
	
	private static final Price CAN_OF_SOUP_PRICE = new Price(new BigDecimal("1.69"), Unit.EACH);
	private static final Price GROUND_BEEF_PRICE = new Price(new BigDecimal("5.49"), Unit.POUND);
	private static final Price BANANAS_PRICE = new Price(new BigDecimal("2.24"), Unit.POUND);
	
	private ShoppingCart shoppingCart;
	private ShoppingCart largeShoppingCart;
	
	@Before
	public void setup() {
		
		this.shoppingCart = new ShoppingCart();
		this.shoppingCart.addItem(CAN_OF_SOUP, new PurchaseAmount(new BigDecimal("3"), Unit.EACH));
		this.shoppingCart.addItem(GROUND_BEEF, new PurchaseAmount(new BigDecimal("2"), Unit.POUND));
		this.shoppingCart.addItem(BANANAS, new PurchaseAmount(new BigDecimal("5"), Unit.POUND));
		
		this.largeShoppingCart = new ShoppingCart();
		this.largeShoppingCart.addItem(CAN_OF_SOUP, new PurchaseAmount(new BigDecimal("6"), Unit.EACH));
		this.largeShoppingCart.addItem(GROUND_BEEF, new PurchaseAmount(new BigDecimal("4"), Unit.POUND));
		this.largeShoppingCart.addItem(BANANAS, new PurchaseAmount(new BigDecimal("10"), Unit.POUND));
	}
	
	/**
	 * Tests a buy 1 get 1 free weekly special.
	 */
	@Test
	public void buy1Get1FreeSubTotal_Test() {
		
		final BuyNGetMAtPercentXOff buy1Get1Free = new BuyNGetMAtPercentXOff(1, 1, new BigDecimal("1"));
		
		final PurchaseAmount canOfSoupPurchaseAmount = this.shoppingCart.getItemCount(CAN_OF_SOUP);
		final PurchaseAmount groundBeefPurchaseAmount = this.shoppingCart.getItemCount(GROUND_BEEF);
		final PurchaseAmount bananasPurchaseAmount = this.shoppingCart.getItemCount(BANANAS);
		
		final BigDecimal canOfSoupSubTotal = buy1Get1Free.getSubTotal(CAN_OF_SOUP, canOfSoupPurchaseAmount, CAN_OF_SOUP_PRICE);
		final BigDecimal groundBeefSubTotal = buy1Get1Free.getSubTotal(GROUND_BEEF, groundBeefPurchaseAmount, GROUND_BEEF_PRICE);
		final BigDecimal bananasSubTotal = buy1Get1Free.getSubTotal(BANANAS, bananasPurchaseAmount, BANANAS_PRICE);
		
		assertEquals(new BigDecimal("3.38"), canOfSoupSubTotal); // 3 cans @ 1.69 -> first=1.69, second=free, third=1.69
		assertEquals(new BigDecimal("5.49"), groundBeefSubTotal); // 2 pounds @ 5.49 -> first=5.49, second=free
		assertEquals(new BigDecimal("6.72"), bananasSubTotal); // 5 pounds @ 2.24 -> first=2.24, second=free, third=2.24, fourth=free, fifth=2.24
	}
	
	/**
	 * Tests a buy 1 get 1 free, limit 6 weekly special
	 */
	@Test
	public void buy1Get1FreeLimit6SubTotal_Test() {
		
		final BuyNGetMAtPercentXOff buy1Get1FreeLimit6 = new BuyNGetMAtPercentXOff(1, 1, new BigDecimal("1"), 6);
		
		final PurchaseAmount canOfSoupPurchaseAmount = this.largeShoppingCart.getItemCount(CAN_OF_SOUP);
		final PurchaseAmount groundBeefPurchaseAmount = this.largeShoppingCart.getItemCount(GROUND_BEEF);
		final PurchaseAmount bananasPurchaseAmount = this.largeShoppingCart.getItemCount(BANANAS);
		
		final BigDecimal canOfSoupSubTotal = buy1Get1FreeLimit6.getSubTotal(CAN_OF_SOUP, canOfSoupPurchaseAmount, CAN_OF_SOUP_PRICE);
		final BigDecimal groundBeefSubTotal = buy1Get1FreeLimit6.getSubTotal(GROUND_BEEF, groundBeefPurchaseAmount, GROUND_BEEF_PRICE);
		final BigDecimal bananasSubTotal = buy1Get1FreeLimit6.getSubTotal(BANANAS, bananasPurchaseAmount, BANANAS_PRICE);
		
		assertEquals(new BigDecimal("5.07"), canOfSoupSubTotal); // 6 cans @ 1.69 | buy1Get1FreeLimit6 | -> 1:1.69, 2:free, 3:1.69, 4:free, 5:1.69, 6:free
		assertEquals(new BigDecimal("10.98"), groundBeefSubTotal); // 4 pounds @ 5.49 | buy1Get1FreeLimit6 | -> 1:5.49, 2: free, 3:5.49, 4:free
		assertEquals(new BigDecimal("15.68"), bananasSubTotal); // 10 pounds @ 2.24 | buy1Get1FreeLimit6 | -> 1:2.24, 2:free, 3:2.24, 4:free, 5:2.24, 6:free, 7,8,9,10 @ 2.24
	}
	
	/**
	 * Tests a buy 2 get 1 50% off weekly special.
	 */
	@Test
	public void buy2Get1HalfOff_Test() {
		
		final BuyNGetMAtPercentXOff buy2Get1HalfOff = new BuyNGetMAtPercentXOff(2, 1, new BigDecimal("0.50"));
		
		final PurchaseAmount canOfSoupPurchaseAmount = this.shoppingCart.getItemCount(CAN_OF_SOUP);
		final PurchaseAmount groundBeefPurchaseAmount = this.shoppingCart.getItemCount(GROUND_BEEF);
		final PurchaseAmount bananasPurchaseAmount = this.shoppingCart.getItemCount(BANANAS);
		
		final BigDecimal canOfSoupSubTotal = buy2Get1HalfOff.getSubTotal(CAN_OF_SOUP, canOfSoupPurchaseAmount, CAN_OF_SOUP_PRICE);
		final BigDecimal groundBeefSubTotal = buy2Get1HalfOff.getSubTotal(GROUND_BEEF, groundBeefPurchaseAmount, GROUND_BEEF_PRICE);
		final BigDecimal bananasSubTotal = buy2Get1HalfOff.getSubTotal(BANANAS, bananasPurchaseAmount, BANANAS_PRICE);
		
		assertEquals(new BigDecimal("4.23"), canOfSoupSubTotal); // 3 cans @ 1.69 -> first=1.69, second=1.69, third=1.69
		assertEquals(new BigDecimal("10.98"), groundBeefSubTotal); // 2 pounds @ 5.49 -> first=5.49, second=5.49 
		assertEquals(new BigDecimal("10.08"), bananasSubTotal); // 5 pounds @ 2.24 -> first=2.24, second=2.24, third=1.12, fourth=2.24, fifth=2.24
	}
}
