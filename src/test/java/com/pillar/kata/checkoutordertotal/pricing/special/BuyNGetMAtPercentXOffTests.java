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
	
	private static final PurchaseAmount CAN_OF_SOUP_PURCHASE_AMOUNT = new PurchaseAmount(new BigDecimal("3"), Unit.EACH);
	private static final PurchaseAmount GROUND_BEEF_PURCHASE_AMOUNT = new PurchaseAmount(new BigDecimal("2"), Unit.POUND);
	private static final PurchaseAmount BANANAS_PURCHASE_AMOUNT = new PurchaseAmount(new BigDecimal("5"), Unit.POUND);
	
	private static final PurchaseAmount CAN_OF_SOUP_PURCHASE_LARGE_AMOUNT = new PurchaseAmount(new BigDecimal("6"), Unit.EACH);
	private static final PurchaseAmount GROUND_BEEF_PURCHASE_LARGE_AMOUNT = new PurchaseAmount(new BigDecimal("4"), Unit.POUND);
	private static final PurchaseAmount BANANAS_PURCHASE_LARGE_AMOUNT = new PurchaseAmount(new BigDecimal("10"), Unit.POUND);
	
	/**
	 * Tests a buy 1 get 1 free weekly special.
	 */
	@Test
	public void buy1Get1FreeSubTotal_Test() {
		
		final BuyNGetMAtPercentXOff buy1Get1Free = new BuyNGetMAtPercentXOff(1, 1, new BigDecimal("1"));
		
		final BigDecimal canOfSoupSubTotal = buy1Get1Free.getSubTotal(CAN_OF_SOUP, CAN_OF_SOUP_PURCHASE_AMOUNT, CAN_OF_SOUP_PRICE);
		final BigDecimal groundBeefSubTotal = buy1Get1Free.getSubTotal(GROUND_BEEF, GROUND_BEEF_PURCHASE_AMOUNT, GROUND_BEEF_PRICE);
		final BigDecimal bananasSubTotal = buy1Get1Free.getSubTotal(BANANAS, BANANAS_PURCHASE_AMOUNT, BANANAS_PRICE);
		
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
		
		final BigDecimal canOfSoupSubTotal = buy1Get1FreeLimit6.getSubTotal(CAN_OF_SOUP, CAN_OF_SOUP_PURCHASE_LARGE_AMOUNT, CAN_OF_SOUP_PRICE);
		final BigDecimal groundBeefSubTotal = buy1Get1FreeLimit6.getSubTotal(GROUND_BEEF, GROUND_BEEF_PURCHASE_LARGE_AMOUNT, GROUND_BEEF_PRICE);
		final BigDecimal bananasSubTotal = buy1Get1FreeLimit6.getSubTotal(BANANAS, BANANAS_PURCHASE_LARGE_AMOUNT, BANANAS_PRICE);
		
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
		
		final BigDecimal canOfSoupSubTotal = buy2Get1HalfOff.getSubTotal(CAN_OF_SOUP, CAN_OF_SOUP_PURCHASE_AMOUNT, CAN_OF_SOUP_PRICE);
		final BigDecimal groundBeefSubTotal = buy2Get1HalfOff.getSubTotal(GROUND_BEEF, GROUND_BEEF_PURCHASE_AMOUNT, GROUND_BEEF_PRICE);
		final BigDecimal bananasSubTotal = buy2Get1HalfOff.getSubTotal(BANANAS, BANANAS_PURCHASE_AMOUNT, BANANAS_PRICE);
		
		assertEquals(new BigDecimal("4.23"), canOfSoupSubTotal); // 3 cans @ 1.69 -> first=1.69, second=1.69, third=.85
		assertEquals(new BigDecimal("10.98"), groundBeefSubTotal); // 2 pounds @ 5.49 -> first=5.49, second=5.49 
		assertEquals(new BigDecimal("10.08"), bananasSubTotal); // 5 pounds @ 2.24 -> first=2.24, second=2.24, third=1.12, fourth=2.24, fifth=2.24
	}
	
	/**
	 * Tests a buy 2 get 1 50% off limit 6 weekly special.
	 */
	@Test
	public void buy2Get1HalfOffLimit6_Test() {
		
		final BuyNGetMAtPercentXOff buy2Get1HalfOffLimit6 = new BuyNGetMAtPercentXOff(2, 1, new BigDecimal("0.50"), 6);
		
		final BigDecimal canOfSoupSubTotal = buy2Get1HalfOffLimit6.getSubTotal(CAN_OF_SOUP, CAN_OF_SOUP_PURCHASE_LARGE_AMOUNT, CAN_OF_SOUP_PRICE);
		final BigDecimal groundBeefSubTotal = buy2Get1HalfOffLimit6.getSubTotal(GROUND_BEEF, GROUND_BEEF_PURCHASE_LARGE_AMOUNT, GROUND_BEEF_PRICE);
		final BigDecimal bananasSubTotal = buy2Get1HalfOffLimit6.getSubTotal(BANANAS, BANANAS_PURCHASE_LARGE_AMOUNT, BANANAS_PRICE);
		
		assertEquals(new BigDecimal("8.46"), canOfSoupSubTotal); // 6 cans @ 1.69 -> 1:1.69, 2:1.69, 3:.85, 4:1.69, 5:1.69, 6:.85
		assertEquals(new BigDecimal("19.22"), groundBeefSubTotal); // 4 pounds @ 5.49 -> 1:5.49, 2:5.49, 3:2.75, 4:5.49
		assertEquals(new BigDecimal("20.16"), bananasSubTotal); // 10 pounds @ 2.24 -> 1:2.24, 2:2.24, 3:1.12, 4:2.24, 5:2.24, 6:1.12, 7:2.24, 8:2.24, 9:2.24, 10:2.24
	}
}
