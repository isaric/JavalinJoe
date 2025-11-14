package com.pathvariable.coffee.model;

import java.util.UUID;

/**
 * Order model stored in the in-memory repository.
 * Using a Java record keeps it simple and immutable.
 */
public record Order(UUID id, String customerName, String drink, boolean isReady) {

    public static Order newOrder(String customerName, String drink) {
        return new Order(UUID.randomUUID(), customerName, drink, false);
    }
}
