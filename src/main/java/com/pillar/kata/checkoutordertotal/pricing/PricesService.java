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
		
		BigDecimal basePriceSubTotal = new BigDecimal("0");
		
		for (Entry<Item, PurchaseAmount> entry : shoppingCart.getItems().entrySet()) {
			final Item shoppingCartItem = entry.getKey();
			final PurchaseAmount purchaseAmount = entry.getValue();
			
			if (shoppingCartItem.equals(item) && this.prices.hasBasePrice(item)) {
				
				final Price basePrice = this.prices.getBasePriceOfItem(item);
				
				final BigDecimal itemSubTotal = calculateItemSubTotalBasePrice(basePrice, purchaseAmount);
				
				basePriceSubTotal = basePriceSubTotal.add(itemSubTotal);
			}
		}
		
		return basePriceSubTotal;
	}
	
	/**
	 * Calculates an {@link Item}'s sub total with markdown.
	 * 
	 * @param shoppingCart the {@link ShoppingCart}
	 * @param item the item
	 * @return the dollar amount
	 */
	public BigDecimal getItemSubTotalWithMarkdown(final ShoppingCart shoppingCart, final Item item) {
		
		BigDecimal basePriceSubTotal = new BigDecimal("0");
		
		for (Entry<Item, PurchaseAmount> entry : shoppingCart.getItems().entrySet()) {
			final Item shoppingCartItem = entry.getKey();
			final PurchaseAmount purchaseAmount = entry.getValue();
			
			if (shoppingCartItem.equals(item) && this.prices.hasMarkdownPrice(item)) {
				
				final Price basePrice = this.prices.getBasePriceOfItem(item);
				final Price markdown = this.prices.getItemMarkdown(item);
				final BigDecimal itemSubTotal = this.calculateItemSubTotalWithMarkdown(basePrice, markdown, purchaseAmount);
			
				basePriceSubTotal = basePriceSubTotal.add(itemSubTotal);
			}
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
		
		BigDecimal basePriceSubTotal = new BigDecimal("0");
		
		for (Entry<Item, PurchaseAmount> entry : shoppingCart.getItems().entrySet()) {
			final Item shoppingCartItem = entry.getKey();
			final PurchaseAmount purchaseAmount = entry.getValue();
			
			if (shoppingCartItem.equals(item) && this.prices.getWeeklySpecials().containsKey(item)) {
				
				final Price currenPriceOfItem = this.prices.getCurrentPriceOfItem(item);
				
				final WeeklySpecial weeklySpecial = this.prices.getWeeklySpecials().get(item);
				
				final BigDecimal weeklySpecialSubTotal = weeklySpecial.getSubTotal(item, purchaseAmount, currenPriceOfItem);
				
				basePriceSubTotal = basePriceSubTotal.add(weeklySpecialSubTotal);
			}
		}
		
		return basePriceSubTotal;
	}
	
	/**
	 * 
	 * @param basePrice
	 * @param purchaseAmount
	 * @return
	 */
	private BigDecimal calculateItemSubTotalBasePrice(final Price basePrice, final PurchaseAmount purchaseAmount) {
		
		return basePrice.getAmount().multiply(purchaseAmount.getAmount());
	}
	
	/**
	 * 
	 * @param basePrice
	 * @param markdown
	 * @param purchaseAmount
	 * @return
	 */
	private BigDecimal calculateItemSubTotalWithMarkdown(final Price basePrice, final Price markdown, final PurchaseAmount purchaseAmount) {
		
		return (basePrice.getAmount().subtract(markdown.getAmount())).multiply(purchaseAmount.getAmount());
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
