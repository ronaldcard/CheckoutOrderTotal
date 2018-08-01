package com.pillar.kata.checkoutordertotal.pricing.special;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Weekly Special implementation.
 * 
 * This special is to be applied with the formula: N for $X
 * 
 * @author tony.card
 */
public class NforXDollars implements WeeklySpecial {
	
	private Integer quantity;
	private BigDecimal amount;
	private Integer limit;
	
	/**
	 * Constructor.
	 * 
	 * @param quantity 
	 * @param amount
	 */
	public NforXDollars(Integer quantity, BigDecimal amount) {
		super();
		this.quantity = quantity;
		this.amount = amount;
	}

	/**
	 * Simple Getter.
	 * 
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return this.quantity;
	}
	
	/**
	 * Simple Setter.
	 * 
	 * @param quantity the quantity to set
	 */
	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
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
	 * @return the limit
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**
	 * Simple Setter.
	 * 
	 * @param limit the limit to set
	 */
	public void setLimit(final Integer limit) {
		this.limit = limit;
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
