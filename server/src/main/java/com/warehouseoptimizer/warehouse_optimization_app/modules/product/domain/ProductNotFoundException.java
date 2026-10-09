package com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " not found!");
    }
}