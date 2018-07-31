package com.pillar.kata.checkoutordertotal.item;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * An object that represents an {@link Item}'s Price and Units.
 * 
 * @author tony.card
 */
public class Price {
	
	private BigDecimal amount;
	private Unit unit;
	
	/**
	 * Constructor.
	 * 
	 * @param amount the amount
	 * @param unit the unit
	 */
	public Price(final BigDecimal amount, final Unit unit) {
		this.amount = amount;
		this.unit = unit;
	}
	
	/**
	 * Simple Getter.
	 * 
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return this.amount;
	}

	/**
	 * Simple Setter.
	 * 
	 * @param amount the amount to set
	 */
	public void setAmount(final BigDecimal amount) {
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
