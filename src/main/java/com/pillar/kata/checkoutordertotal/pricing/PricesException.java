package com.pillar.kata.checkoutordertotal.pricing;

/**
 * Custom {@link Prices} {@link RuntimeException}.
 * 
 * @author tony.card
 */
public class PricesException extends RuntimeException {
	
	/**
	 * Empty Constructor.
	 */
	public PricesException() {
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message the message
	 */
	public PricesException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param cause the cause
	 */
	public PricesException(final Throwable cause) {
		super(cause);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public PricesException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
