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
	private Map<Item, PurchaseAmount> items;
	
	/**
	 * Empty Constructor.
	 */
	public ShoppingCart() {
		
		this.items = new HashMap<>();
	}
	
	/**
	 * Add's an {@link Item} to the shopping cart.
	 * 
	 * @param item the item
	 */
	public void addItem(final Item item, final PurchaseAmount addPurchaseAmount) {
		
		if (item == null || addPurchaseAmount == null) {
			return;
		}
		
		if (this.items.containsKey(item)) {
			final PurchaseAmount purchaseAmount = this.items.get(item);
			final float currentCount = purchaseAmount.getAmount();
						
			purchaseAmount.setAmount(currentCount + addPurchaseAmount.getAmount());
		} else {
			
			this.items.put(item, addPurchaseAmount);
		}
	}
	
	/**
	 * Gets the number of the type of {@link Item} in the shopping cart.
	 * 
	 * @param item the item
	 * @return the count
	 */
	public PurchaseAmount getItemCount(final Item item) {
		
		return this.items.get(item);
	}

	/**
	 * Simple Getter.
	 * 
	 * @return the items
	 */
	public Map<Item, PurchaseAmount> getItems() {
		return this.items;
	}

	/**
	 * Simple Setter.
	 * 
	 * @param items the items to set
	 */
	public void setItems(final Map<Item, PurchaseAmount> items) {
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
}
