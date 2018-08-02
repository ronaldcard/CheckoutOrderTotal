package com.pillar.kata.checkoutordertotal.pricing;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.pillar.kata.checkoutordertotal.item.Item;
import com.pillar.kata.checkoutordertotal.item.Price;
import com.pillar.kata.checkoutordertotal.pricing.special.WeeklySpecial;

/**
 * Pricing Service.
 * 
 * @author tony.card
 */
@Component
public class Prices {
	
	private Map<Item, Price> basePrices;
	private Map<Item, Price> markdownPrices;
	private Map<Item, WeeklySpecial> weeklySpecials;
	
	/**
	 * Initializes the item prices.
	 */
	@PostConstruct
	public void initializeItemPrices() {
		
		this.basePrices = new HashMap<>();
		this.markdownPrices = new HashMap<>();
		this.weeklySpecials = new HashMap<>();
		// potentially load from repository, if we plan to persist the inventory
	}
	
	/**
	 * Gets the current {@Link Price} of an {@link Item}.  basePrice - markdown.
	 * 
	 * @param item the Item
	 * @return the current price
	 */
	public Price getCurrentPriceOfItem(final Item item) {
		
		// TODO - validation??
		final Price basePrice = getBasePriceOfItem(item);
		final Price markdown = getItemMarkdown(item);
		
		final Price currentPrice = new Price(basePrice.getAmount().subtract(markdown.getAmount()), basePrice.getUnit());
		
		return currentPrice;
	}
	
	/**
	 * Gets the base {@link Price} of an {@link Item} in the inventory.
	 * 
	 * @param item the item
	 * @return the price
	 */
	public Price getBasePriceOfItem(final Item item) {
		
		if (hasBasePrice(item)) {
			return this.basePrices.get(item);
		}
		
		throw new PricesException("No base price is configured for [" + item.getDescription() + "]");
	}
	
	/**
	 * Determines if there is a base price for an {@link Item}.
	 * 
	 * @param item the Item
	 * @return true if it does; false otherwise
	 */
	public boolean hasBasePrice(final Item item) {
		return this.basePrices.containsKey(item);
	}
	
	/**
	 * Sets the base {@link Price} of an {@link Item}.
	 * 
	 * @param item the item to set
	 * @param price the price to set
	 */
	public void setBasePriceOfItem(final Item item, final Price price) {
		
		if (item == null || price == null) {
			return;
		}
		
		this.basePrices.put(item, price);
	}
	
	/**
	 * Gets the markdown {@link Price} of an {@link Item} in the inventory.
	 * 
	 * @param item the item
	 * @return the markdown price
	 */
	public Price getItemMarkdown(final Item item) {
		
		if (hasMarkdownPrice(item)) {
			return this.markdownPrices.get(item);
		}
		
		throw new PricesException("No markdown price is configured for [" + item.getDescription() + "]");
	}
	
	/**
	 * Determines if there is a markdown for an {@link Item}.
	 * 
	 * @param item the Item
	 * @return true if it does; false otherwise
	 */
	public boolean hasMarkdownPrice(final Item item) {
		return this.markdownPrices.containsKey(item);
	}
	
	/**
	 * Sets the markdown {@link Price} of an {@link Item}.
	 * 
	 * @param item the item to set
	 * @param price the price to set
	 */
	public void setItemMarkdown(final Item item, final Price price) { 
		
		if (item == null || price == null) {
			return;
		}
		
		this.markdownPrices.put(item, price);
	}
	
	/**
	 * Gets the {@link WeeklySpecial} for an {@link Item}.
	 * 
	 * @param item the item
	 * @return the special
	 */
	public WeeklySpecial getItemWeeklySpecial(final Item item) {
		
		if (this.weeklySpecials.containsKey(item)) {
			return this.weeklySpecials.get(item);
		}
		
		throw new PricesException("No weekly specials configured for [" + item.getDescription() + "]");
	}
	
	/**
	 * Sets the {@link WeeklySpecial} for an {@link Item}.
	 * 
	 * @param item the item
	 * @param special the special
	 */
	public void setItemWeeklySpecial(final Item item, final WeeklySpecial special) {
		
		if (item == null || special == null) {
			return;
		}
		
		this.weeklySpecials.put(item, special);
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

	/**
	 * Simple Getter.
	 * 
	 * @return the markdownPrices
	 */
	public Map<Item, Price> getMarkdownPrices() {
		return markdownPrices;
	}

	/**
	 * Simple Setter.
	 * 
	 * @param markdownPrices the markdownPrices to set
	 */
	public void setMarkdownPrices(Map<Item, Price> markdownPrices) {
		this.markdownPrices = markdownPrices;
	}

	/**
	 * Simple Getter.
	 * 
	 * @return the weeklySpecials
	 */
	public Map<Item, WeeklySpecial> getWeeklySpecials() {
		return this.weeklySpecials;
	}

	/**
	 * Simple Setter.
	 * 
	 * @param weeklySpecials the weeklySpecials to set
	 */
	public void setWeeklySpecials(final Map<Item, WeeklySpecial> weeklySpecials) {
		this.weeklySpecials = weeklySpecials;
	}
}
