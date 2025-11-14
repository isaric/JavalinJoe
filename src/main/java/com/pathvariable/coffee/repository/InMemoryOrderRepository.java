package com.pathvariable.coffee.repository;

import com.pathvariable.coffee.model.Order;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * A simple thread-safe in-memory repository for Orders.
 */
public class InMemoryOrderRepository {
    private final Map<UUID, Order> store = new ConcurrentHashMap<>();

    public Order save(Order order) {
        store.put(order.id(), order);
        return order;
    }

    public List<Order> findAll() {
        return new ArrayList<>(store.values());
    }

    public List<Order> findAllActive() {
        return store.values().stream().filter(Predicate.not(Order::isReady)).toList();
    }

    public Optional<Order> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * Finds orders by exact customer name.
     * Intentionally not implemented as per current scaffold requirements.
     *
     * @param customerName the customer name to filter by
     * @return list of orders for the given name
     * @throws UnsupportedOperationException always, until implemented
     */
    public List<Order> findByCustomerName(String customerName) {
        throw new UnsupportedOperationException("findByCustomerName is not implemented yet");
    }

    public Optional<Order> markReady(UUID id) {
        return Optional.ofNullable(store.computeIfPresent(id, (k, v) -> new Order(v.id(), v.customerName(), v.drink(), true)));
    }

    public boolean delete(UUID id) {
        return store.remove(id) != null;
    }
}
