package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "store")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 128)
    private String city;

    @Column(name = "warehouse_id", nullable = false, length = 64)
    private String warehouseId;

    @Column(name = "capacity_m3", nullable = false)
    private double capacityM3;

    protected StoreEntity() {
    }

    public StoreEntity(String name, String address, String city, String warehouseId, double capacityM3) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.warehouseId = warehouseId;
        this.capacityM3 = capacityM3;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getWarehouseId() { return warehouseId; }
    public double getCapacityM3() { return capacityM3; }
}
