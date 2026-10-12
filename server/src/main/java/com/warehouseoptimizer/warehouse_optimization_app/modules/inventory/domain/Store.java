package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain;

public final class Store {

    private final Long id;
    private final String name;
    private final String address;
    private final String city;
    private final String warehouseId;
    private final double capacityM3;

    public Store(
            Long id,
            String name,
            String address,
            String city,
            String warehouseId,
            double capacityM3) {
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
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.warehouseId = warehouseId;
        this.capacityM3 = capacityM3;
    }

    public Long id() { return id; }
    public String name() { return name; }
    public String address() { return address; }
    public String city() { return city; }
    public String warehouseId() { return warehouseId; }
    public double capacityM3() { return capacityM3; }
}
