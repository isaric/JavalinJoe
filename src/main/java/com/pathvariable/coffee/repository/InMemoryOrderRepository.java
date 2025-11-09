package com.pathvariable.coffee.repository;

import com.pathvariable.coffee.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A simple thread-safe in-memory repository for Orders.
 */
public class InMemoryOrderRepository {
    private final Map<String, Order> store = new ConcurrentHashMap<>();

    public Order save(Order order) {
        store.put(order.id(), order);
        return order;
    }

    public List<Order> findAll() {
        return new ArrayList<>(store.values());
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

    public Optional<Order> markReady(String id) {
        return Optional.ofNullable(store.computeIfPresent(id, (k, v) -> new Order(v.id(), v.customerName(), v.drink(), true)));
    }

    public boolean delete(String id) {
        return store.remove(id) != null;
    }
}
