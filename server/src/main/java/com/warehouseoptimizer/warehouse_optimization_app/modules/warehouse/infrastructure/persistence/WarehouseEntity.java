package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "warehouse")
public class WarehouseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "max_capacity", nullable = false)
    private double capacity;

    @Column(name = "active", nullable = false)
    private boolean active;

    protected WarehouseEntity() {
    }

    public WarehouseEntity(String name, double latitude, double longitude, double capacity, boolean active) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacity = capacity;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getCapacity() {
        return capacity;
    }

    public boolean isActive() {
        return active;
    }
}
