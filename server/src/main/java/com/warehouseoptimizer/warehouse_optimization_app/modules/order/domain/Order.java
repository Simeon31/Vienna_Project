
package com.warehouseoptimizer.warehouse_optimization_app.modules.order.domain;

import java.time.LocalDateTime;

public class Order {

    private final Long id;
    private OrderStatus status;
    private final LocalDateTime createdAt;

    public Order(Long id, OrderStatus status, LocalDateTime createdAt) {
        if (status == null) {
            throw new IllegalArgumentException("Order status cannot be null");
        }

        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isPending() {
        return status == OrderStatus.PENDING;
    }
}
