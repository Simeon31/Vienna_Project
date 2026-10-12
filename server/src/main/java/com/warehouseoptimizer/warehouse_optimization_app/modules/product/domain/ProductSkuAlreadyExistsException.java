package com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain;

public class ProductSkuAlreadyExistsException extends RuntimeException {

    public ProductSkuAlreadyExistsException(String sku) {
        super("Product with SKU " + sku + " already exists!");
    }
}