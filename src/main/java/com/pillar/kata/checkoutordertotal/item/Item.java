package com.pillar.kata.checkoutordertotal.item;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * An object representing an Item.
 * 
 * @author tony.card
 */
public class Item {
	
	private String description;
	
	/**
	 * Constructor.
	 * 
	 * @param description the {@link Item}'s description
	 */
	public Item(final String description) {
		this.description = description;
	}
	
	/**
	 * Simple Getter.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Simple Setter.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
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
