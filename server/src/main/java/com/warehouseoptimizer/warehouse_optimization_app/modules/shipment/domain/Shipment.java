package com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.domain;

import java.time.LocalDateTime;

public final class Shipment {

    private final Long id;
    private final Long warehouseId;
    private final String orderStatus;
    private final LocalDateTime expectedArrival;
    private final LocalDateTime createdAt;

    public Shipment(
            Long id,
            Long warehouseId,
            String orderStatus,
            LocalDateTime expectedArrival,
            LocalDateTime createdAt) {
        if (warehouseId == null) {
            throw new IllegalArgumentException("Shipment warehouse is required");
        }
        if (orderStatus == null || orderStatus.isBlank()) {
            throw new IllegalArgumentException("Shipment order status is required");
        }
        this.id = id;
        this.warehouseId = warehouseId;
        this.orderStatus = orderStatus;
        this.expectedArrival = expectedArrival;
        this.createdAt = createdAt;
    }

    public Long id() {
        return id;
    }

    public Long warehouseId() {
        return warehouseId;
    }

    public String orderStatus() {
        return orderStatus;
    }

    public LocalDateTime expectedArrival() {
        return expectedArrival;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
