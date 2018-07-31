package com.pillar.kata.checkoutordertotal.item;

/**
 * Enumeration of Pricing Units.
 * 
 * @author tony.card
 */
public enum Unit {
	
	EACH("Each"),
	POUND("Per Pound");
	
	private String description;
	
	/**
	 * Private Constructor.
	 * 
	 * @param description the description
	 */
	private Unit(final String description) {
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
}
