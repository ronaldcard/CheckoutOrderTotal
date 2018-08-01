package com.pillar.kata.checkoutordertotal.shoppingcart;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.pillar.kata.checkoutordertotal.item.Item;

/**
 * Object representing a Shopping Cart.
 * 
 * @author tony.card
 */
public class ShoppingCart {
	
	// item | count
	private Map<Item, Integer> items;
	
	/**
	 * Empty Constructor.
	 */
	public ShoppingCart() {
		
		this.items = new HashMap<>();
	}

	/**
	 * Simple Getter.
	 * 
	 * @return the items
	 */
	public Map<Item, Integer> getItems() {
		return this.items;
	}

	/**
	 * Simple Setter.
	 * 
	 * @param items the items to set
	 */
	public void setItems(final Map<Item, Integer> items) {
		this.items = items;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
	
	/**
	 * Add's an {@link Item} to the shopping cart.
	 * 
	 * @param item the item
	 */
	public void addItem(final Item item, final int addCount) {
		
		if (this.items.containsKey(item)) {
			final Integer currentCount = this.items.get(item);
			System.out.println("[" + currentCount + "][" + addCount + "]");
			this.items.put(item, (currentCount + addCount));
		} else {
			System.out.println("adding [" + addCount + "]");
			this.items.put(item, addCount);
		}
	}
	
	/**
	 * Gets the number of the type of {@link Item} in the shopping cart.
	 * 
	 * @param item the item
	 * @return the count
	 */
	public int getItemCount(final Item item) {
		
		return this.items.containsKey(item) ? this.items.get(item) : 0;
	}
}
