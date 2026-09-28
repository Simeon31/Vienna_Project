package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain;

public final class Warehouse {

    private final Long id;
    private final String name;
    private final double latitude;
    private final double longitude;
    private final double capacity;
    private final boolean active;

    public Warehouse(Long id, String name, double latitude, double longitude, double capacity, boolean active) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Warehouse name is required");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacity = capacity;
        this.active = active;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public double latitude() {
        return latitude;
    }

    public double longitude() {
        return longitude;
    }

    public double capacity(){
        return capacity;
    }

    public boolean active(){
        return active;
    }
}
