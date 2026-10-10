package com.warehouseoptimizer.warehouse_optimization_app.modules.product.application.ports.in;

import com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain.Product;

import java.util.List;

/** Input port: what the product management use case offers. */
public interface ManageProductUseCase {

    Product createProduct(String sku, String name, String category,
                          double lengthCm, double widthCm, double heightCm, double weightKg);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product updateProduct(Long id, String sku, String name, String category,
                          double lengthCm, double widthCm, double heightCm, double weightKg);
}