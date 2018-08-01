package com.pillar.kata.checkoutordertotal.pricing.special;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Weekly Special implementation.
 * 
 * This special is to be applied with the formula: buy N get M items at %X off.
 * 
 * @author tony.card
 */
public class BuyNGetMAtPercentXOff implements WeeklySpecial {
	
	private Integer buyCount;
	private Integer quantity;
	private BigDecimal percentageOff;
	private Integer limit;
	
	/**
	 * Constructor.
	 * 
	 * @param buyCount the number of {@link Items} the customer is purchasing
	 * @param quantity the number of similar Items that will be discounted on an order
	 * @param percentageOff the percentage of discount applied
	 */
	public BuyNGetMAtPercentXOff(final Integer buyCount, final Integer quantity, final BigDecimal percentageOff) {
		this.buyCount = buyCount;
		this.quantity = quantity;
		this.percentageOff = percentageOff;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param buyCount the number of {@link Items} the customer is purchasing
	 * @param quantity the number of similar Items that will be discounted on an order
	 * @param percentageOff the percentage of discount applied
	 * @param limit the number of items to apply the discount to
	 */
	public BuyNGetMAtPercentXOff(final Integer buyCount, final Integer quantity, final BigDecimal percentageOff, final Integer limit) {
		this.buyCount = buyCount;
		this.quantity = quantity;
		this.percentageOff = percentageOff;
		this.limit = limit;
	}

	/**
	 * Simple Getter.
	 * 
	 * @return the buyCount
	 */
	public Integer getBuyCount() {
		return this.buyCount;
	}

	/**
	 * Simple Setter.
	 * 
	 * @param buyCount the buyCount to set
	 */
	public void setBuyCount(final Integer buyCount) {
		this.buyCount = buyCount;
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
	 * @return the percentageOff
	 */
	public BigDecimal getPercentageOff() {
		return this.percentageOff;
	}

	/**
	 * Simple Setter.
	 * 
	 * @param percentageOff the percentageOff to set
	 */
	public void setPercentageOff(final BigDecimal percentageOff) {
		this.percentageOff = percentageOff;
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
