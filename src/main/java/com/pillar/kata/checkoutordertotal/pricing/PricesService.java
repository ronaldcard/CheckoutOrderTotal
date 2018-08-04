package com.pillar.kata.checkoutordertotal.pricing;

import java.math.BigDecimal;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pillar.kata.checkoutordertotal.item.Item;
import com.pillar.kata.checkoutordertotal.item.Price;
import com.pillar.kata.checkoutordertotal.pricing.special.WeeklySpecial;
import com.pillar.kata.checkoutordertotal.shoppingcart.PurchaseAmount;
import com.pillar.kata.checkoutordertotal.shoppingcart.ShoppingCart;

/**
 * Prices Service.
 * 
 * @author tony.card
 */
@Service
public class PricesService {
	
	@Autowired
	private Prices prices;
	
	/**
	 * Calculates an {@link Item}'s sub total base price. 
	 * 
	 * @param shoppingCart the {@link ShoppingCart}
	 * @param item the item
	 * @return the dollar amount
	 */
	public BigDecimal getItemSubTotalBasePrice(final ShoppingCart shoppingCart, final Item item) {
		
		BigDecimal basePriceSubTotal = BigDecimal.ZERO;
		
		final PurchaseAmount purchaseAmount = shoppingCart.getItemCount(item);
		
		if (purchaseAmount != null && this.prices.hasBasePrice(item)) {
			
			final Price basePrice = this.prices.getBasePriceOfItem(item);
			
			final BigDecimal itemSubTotal = calculateItemSubTotal(basePrice, purchaseAmount);
			
			basePriceSubTotal = basePriceSubTotal.add(itemSubTotal);
		}
		
		return basePriceSubTotal;
	}
	
	/**
	 * Calculates an {@link Item}'s current sub total with markdown.
	 * 
	 * @param shoppingCart the {@link ShoppingCart}
	 * @param item the item
	 * @return the dollar amount
	 */
	public BigDecimal getItemCurrentSubTotal(final ShoppingCart shoppingCart, final Item item) {
		
		BigDecimal basePriceSubTotal = BigDecimal.ZERO;
		
		final PurchaseAmount purchaseAmount = shoppingCart.getItemCount(item);
		
		if (purchaseAmount != null) {
			
			final Price currenPriceOfItem = this.prices.getCurrentPriceOfItem(item);
			final BigDecimal itemSubTotal = this.calculateItemSubTotal(currenPriceOfItem, purchaseAmount);
		
			basePriceSubTotal = basePriceSubTotal.add(itemSubTotal);
		}
		
		return basePriceSubTotal;
	}
	
	/**
	 * Calculates an {@link Item}'s sub total with the {@link WeeklySpecial}.
	 * 
	 * @param shoppingCart the {@link ShoppingCart}
	 * @param item the Item
	 * @return the dollar amount
	 */
	public BigDecimal getItemSubTotalWeeklySpecial(final ShoppingCart shoppingCart, final Item item) {
		
		BigDecimal basePriceSubTotal = BigDecimal.ZERO;
		
		final PurchaseAmount purchaseAmount = shoppingCart.getItemCount(item);
			
		if (purchaseAmount != null && this.prices.hasWeeklySpecial(item)) {
			
			final Price currenPriceOfItem = this.prices.getCurrentPriceOfItem(item);
			
			final WeeklySpecial weeklySpecial = this.prices.getWeeklySpecials().get(item);
			
			final BigDecimal weeklySpecialSubTotal = weeklySpecial.getSubTotal(item, purchaseAmount, currenPriceOfItem);
			
			basePriceSubTotal = basePriceSubTotal.add(weeklySpecialSubTotal);
		}
		
		return basePriceSubTotal;
	}
	
	/**
	 * Calculates the current sub total of the {@link ShoppingCart}.  This includes the weekly specials & markdowns.
	 * 
	 * @param shoppingCart the ShoppingCart
	 * @return the dollar amount
	 */
	public BigDecimal getSubTotalOfShoppingCart(final ShoppingCart shoppingCart) {
		
		BigDecimal subTotal = BigDecimal.ZERO;
		
		for (Entry<Item, PurchaseAmount> entry : shoppingCart.getItems().entrySet()) {
			final Item item = entry.getKey();
			final PurchaseAmount purchaseAmount = entry.getValue();
			final Price currenPriceOfItem = this.prices.getCurrentPriceOfItem(item);
			
			if (this.prices.hasWeeklySpecial(item)) {
				
				final WeeklySpecial weeklySpecial = this.prices.getWeeklySpecials().get(item);
				
				final BigDecimal weeklySpecialSubTotal = weeklySpecial.getSubTotal(item, purchaseAmount, currenPriceOfItem);
				
				subTotal = subTotal.add(weeklySpecialSubTotal);
			} else {
				
				final BigDecimal itemSubTotal = this.calculateItemSubTotal(currenPriceOfItem, purchaseAmount);
				
				subTotal = subTotal.add(itemSubTotal);
			}
		}
		
		return subTotal;
	}
	
	/**
	 * Calculates the items sub total.
	 * 
	 * @param price the {@link Price}
	 * @param purchaseAmount the {@link PurchaseAmount}
	 * @return the dollar amount
	 */
	private BigDecimal calculateItemSubTotal(final Price price, final PurchaseAmount purchaseAmount) {
		
		return price.getAmount().multiply(purchaseAmount.getAmount());
	}
	
	/**
	 * @return the prices
	 */
	public Prices getPrices() {
		return prices;
	}

	/**
	 * @param prices the prices to set
	 */
	public void setPrices(Prices prices) {
		this.prices = prices;
	}
}
