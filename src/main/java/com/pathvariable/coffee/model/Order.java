package com.pathvariable.coffee.model;

/**
 * Order model stored in the in-memory repository.
 * Using a Java record keeps it simple and immutable.
 */
public record Order(String id, String customerName, String drink, boolean isReady) {}
