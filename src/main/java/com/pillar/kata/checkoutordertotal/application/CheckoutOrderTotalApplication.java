package com.pillar.kata.checkoutordertotal.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot application runner.
 * 
 * @author tony.card
 */
@SpringBootApplication
@ComponentScan("com.pillar.kata.checkoutordertotal")
public class CheckoutOrderTotalApplication implements CommandLineRunner {
	
	/**
	 * Main.
	 * 
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CheckoutOrderTotalApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// add any custom run configurations
		
	}
}
