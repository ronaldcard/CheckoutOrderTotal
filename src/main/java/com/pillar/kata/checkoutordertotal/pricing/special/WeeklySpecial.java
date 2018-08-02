package com.pillar.kata.checkoutordertotal.pricing.special;

import java.math.BigDecimal;

import com.pillar.kata.checkoutordertotal.item.Item;
import com.pillar.kata.checkoutordertotal.item.Price;
import com.pillar.kata.checkoutordertotal.shoppingcart.PurchaseAmount;

/**
 * Weekly Special.
 * 
 * @author tony.card
 */
public interface WeeklySpecial {
	
	/**
	 * Calculates the sub total with the weekly special's applied.
	 * 
	 * @param item the {@link Item}
	 * @param purchaseAmount the {@link PurchaseAmount}
	 * @param the price of the item
	 * @param currentSubtTotal the current sub total of the item
	 * @return the dollar amount
	 */
	BigDecimal getSubTotal(final Item item, final PurchaseAmount purchaseAmount, final Price price);
}
