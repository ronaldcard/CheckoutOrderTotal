package com.pillar.kata.checkoutordertotal.pricing;

import java.math.BigDecimal;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pillar.kata.checkoutordertotal.item.Item;
import com.pillar.kata.checkoutordertotal.item.Price;
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
			
			if (shoppingCartItem.equals(item)) {
				
				final Price basePrice = this.prices.getBasePriceOfItem(item);
				
				final BigDecimal itemSubTotal = basePrice.getAmount().multiply(purchaseAmount.getAmount());
				
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
			
			if (shoppingCartItem.equals(item)) {
				final Price basePrice = this.prices.getBasePriceOfItem(item);
				final Price markdown = this.prices.getItemMarkdown(item);
				
				final BigDecimal itemSubTotal = (basePrice.getAmount().subtract(markdown.getAmount())).multiply(purchaseAmount.getAmount());
				
				basePriceSubTotal = basePriceSubTotal.add(itemSubTotal);
			}
		}
		
		return basePriceSubTotal;
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
