package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain;

public final class Store {

    private final Long id;
    private final String name;
    private final String address;
    private final String city;
    private final String warehouseId;
    private final double capacityM3;
    private final double latitude;
    private final double longitude;

    public Store(
            Long id,
            String name,
            String address,
            String city,
            String warehouseId,
            double capacityM3,
            double latitude,
            double longitude) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Store name is required");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Store address is required");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("Store city is required");
        }
        if (warehouseId == null || warehouseId.isBlank()) {
            throw new IllegalArgumentException("Warehouse ID is required");
        }
        if (!Double.isFinite(capacityM3) || capacityM3 <= 0) {
            throw new IllegalArgumentException("Store capacity must be greater than 0 m^3");
        }
        if (!Double.isFinite(latitude) || latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if (!Double.isFinite(longitude) || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.warehouseId = warehouseId;
        this.capacityM3 = capacityM3;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /** Compatibility constructor for stores created before coordinates were captured. */
    public Store(Long id, String name, String address, String city, String warehouseId, double capacityM3) {
        this(id, name, address, city, warehouseId, capacityM3, 0, 0);
    }

    public Long id() { return id; }
    public String name() { return name; }
    public String address() { return address; }
    public String city() { return city; }
    public String warehouseId() { return warehouseId; }
    public double capacityM3() { return capacityM3; }
    public double latitude() { return latitude; }
    public double longitude() { return longitude; }
}
