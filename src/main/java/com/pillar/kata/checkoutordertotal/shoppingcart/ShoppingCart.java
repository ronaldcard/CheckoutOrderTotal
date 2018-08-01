package com.pillar.kata.checkoutordertotal.shoppingcart;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

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
	
	@PostConstruct
	public void initializeItems() {
		
		this.items = new HashMap<>();
		
		// potentially from either repository or client's localStorage?
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
}
