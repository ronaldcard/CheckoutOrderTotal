package com.pillar.kata.checkoutordertotal.pricing;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.pillar.kata.checkoutordertotal.item.Item;
import com.pillar.kata.checkoutordertotal.item.Price;

/**
 * Pricing Service.
 * 
 * @author tony.card
 */
@Service
public class PricingService {
	
	private Map<Item, Price> basePrices;
	
	/**
	 * Initializes the item prices.
	 */
	@PostConstruct
	public void initializeItemPrices() {
		
		this.basePrices = new HashMap<>();
		
		// potentially load from repository, if we plan to persist the inventory
	}
	
	/**
	 * Gets the base {@link Price} of an {@link Item} in the inventory.
	 * 
	 * @param item the item
	 * @return the price
	 */
	public Price getBasePriceOfItem(final Item item) {
		
		if (this.basePrices.containsKey(item)) {
			return this.basePrices.get(item);
		}
		
		throw new PricingServiceException("No base price is configured for [" + item.getDescription() + "]");
	}
	
	/**
	 * Sets the base price of an {@link Item}.
	 * 
	 * @param item the item
	 * @param price the price
	 */
	public void setBasePriceOfItem(final Item item, final Price price) {
		
		if (item == null || price == null) {
			return;
		}
		
		this.basePrices.put(item, price);
	}

	/**
	 * Simple Getter.
	 * 
	 * @return the basePrices
	 */
	public Map<Item, Price> getBasePrices() {
		return this.basePrices;
	}

	/**
	 * Simple Getter.
	 * 
	 * @param basePrices the basePrices to set
	 */
	public void setBasePrices(final Map<Item, Price> basePrices) {
		this.basePrices = basePrices;
	}
}
