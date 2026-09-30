package com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain;

public final class Product {

    private final Long id;
    private final String sku;
    private final String name;
    private final String category;
    private final double lengthCm;
    private final double widthCm;
    private final double heightCm;
    private final double weightKg;

    public Product(Long id, String sku, String name, String category,
                   double lengthCm, double widthCm, double heightCm, double weightKg) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("Product SKU is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (lengthCm <= 0 || widthCm <= 0 || heightCm <= 0) {
            throw new IllegalArgumentException("Dimensions must be greater than 0");
        }
        if (weightKg <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.lengthCm = lengthCm;
        this.widthCm = widthCm;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
    }

    public double volumeM3() {
        return (lengthCm * widthCm * heightCm) / 1_000_000.0;
    }

    public Long id() { return id; }
    public String sku() { return sku; }
    public String name() { return name; }
    public String category() { return category; }
    public double lengthCm() { return lengthCm; }
    public double widthCm() { return widthCm; }
    public double heightCm() { return heightCm; }
    public double weightKg() { return weightKg; }
}