package com.warehouseoptimizer.warehouse_optimization_app.modules.product.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku", nullable = false, unique = true, length = 64)
    private String sku;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "category", length = 64)
    private String category;

    @Column(name = "length_cm", nullable = false)
    private double lengthCm;

    @Column(name = "width_cm", nullable = false)
    private double widthCm;

    @Column(name = "height_cm", nullable = false)
    private double heightCm;

    @Column(name = "weight_kg", nullable = false)
    private double weightKg;

    protected ProductEntity() {
    }

    public ProductEntity(String sku, String name, String category,
                         double lengthCm, double widthCm, double heightCm, double weightKg) {
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.lengthCm = lengthCm;
        this.widthCm = widthCm;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getLengthCm() { return lengthCm; }
    public double getWidthCm() { return widthCm; }
    public double getHeightCm() { return heightCm; }
    public double getWeightKg() { return weightKg; }
}