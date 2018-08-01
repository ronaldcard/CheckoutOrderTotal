package com.pillar.kata.checkoutordertotal.shoppingcart;

/**
 * Custom {@link ShoppingCart} {@link RuntimException}.
 * 
 * @author PatientZero
 */
public class ShoppingCartException extends RuntimeException {
	
	/**
	 * Empty Constructor.
	 */
	public ShoppingCartException() {
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message the message
	 */
	public ShoppingCartException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param cause the cause
	 */
	public ShoppingCartException(final Throwable cause) {
		super(cause);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public ShoppingCartException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
