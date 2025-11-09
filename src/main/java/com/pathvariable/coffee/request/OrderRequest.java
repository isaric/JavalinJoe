package com.pathvariable.coffee.request;

/**
 * Payload for creating an order.
 */
public record OrderRequest(String customerName, String drink) {
    public void validate() {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("customerName is required");
        }
        if (drink == null || drink.isBlank()) {
            throw new IllegalArgumentException("drink is required");
        }
    }
}
