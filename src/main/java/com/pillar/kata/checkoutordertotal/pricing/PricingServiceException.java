package com.pillar.kata.checkoutordertotal.pricing;

/**
 * Custom {@link Prices} {@link RuntimeException}.
 * 
 * @author tony.card
 */
public class PricingServiceException extends RuntimeException {
	
	/**
	 * Empty Constructor.
	 */
	public PricingServiceException() {
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message the message
	 */
	public PricingServiceException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param cause the cause
	 */
	public PricingServiceException(final Throwable cause) {
		super(cause);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public PricingServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
