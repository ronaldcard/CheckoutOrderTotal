package com.pillar.kata.checkoutordertotal.pricing.special;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.pillar.kata.checkoutordertotal.item.Item;
import com.pillar.kata.checkoutordertotal.item.Price;
import com.pillar.kata.checkoutordertotal.shoppingcart.PurchaseAmount;

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
	 * {@inheritDoc}
	 */
	@Override
	public BigDecimal getSubTotal(final Item item, final PurchaseAmount purchaseAmount, final Price price) {
				
		BigDecimal subTotal = new BigDecimal("0");
		
		int currentQuantity = this.quantity - 1;
		for (int i = purchaseAmount.getAmount().intValue(); i > 0; i--) {
			
			subTotal = subTotal.add(price.getAmount());
			
			if (currentQuantity == 0) {
				// subtract the amount
				final BigDecimal subtractAmount = price.getAmount().multiply(new BigDecimal(this.quantity));
				subTotal = subTotal.subtract(subtractAmount);
				
				// add the for amount
				subTotal = subTotal.add(this.amount);
				
				currentQuantity = this.quantity;
			} else {
				currentQuantity--;
			}
		}
		
		return subTotal;
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
