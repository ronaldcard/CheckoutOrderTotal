package com.pillar.kata.checkoutordertotal.shoppingcart;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.pillar.kata.checkoutordertotal.item.Unit;

/**
 * Purchase amount.
 * 
 * @author tony.card
 */
public class PurchaseAmount {
	
	private Double amount;
	private Unit unit;
	
	/**
	 * Constructor.
	 * 
	 * @param amount the amount
	 * @param unit the unit
	 */
	public PurchaseAmount(final Double amount, final Unit unit) {
		this.amount = amount;
		this.unit = unit;
	}
	
	/**
	 * Simple Getter.
	 * 
	 * @return the amount
	 */
	public Double getAmount() {
		return this.amount;
	}
	
	/**
	 * Simple Setter.
	 * 
	 * @param amount the amount to set
	 */
	public void setAmount(final Double amount) {
		this.amount = amount;
	}
	
	/**
	 * Simple Getter.
	 * 
	 * @return the unit
	 */
	public Unit getUnit() {
		return this.unit;
	}
	
	/**
	 * Simple Setter.
	 * 
	 * @param unit the unit to set
	 */
	public void setUnit(final Unit unit) {
		this.unit = unit;
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
