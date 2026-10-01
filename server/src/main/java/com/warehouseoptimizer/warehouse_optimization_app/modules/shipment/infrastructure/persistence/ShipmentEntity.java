package com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipment")
public class ShipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @Column(name = "order_status", nullable = false, length = 32)
    private String orderStatus;

    @Column(name = "expected_arrival")
    private LocalDateTime expectedArrival;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected ShipmentEntity() {
    }

    public Long getId() { return id; }
    public Long getWarehouseId() { return warehouseId; }
    public String getOrderStatus() { return orderStatus; }
    public LocalDateTime getExpectedArrival() { return expectedArrival; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
