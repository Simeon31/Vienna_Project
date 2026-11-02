package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain;

public final class Warehouse {

    private final Long id;
    private final String name;
    private final String address;
    private final double latitude;
    private final double longitude;
    private final double capacity;
    private final boolean active;

    public Warehouse(Long id, String name, String address, double latitude, double longitude, double capacity, boolean active) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Warehouse name is required");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Warehouse address is required");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String address() {
        return address;
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
