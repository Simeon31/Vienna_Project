package com.warehouseoptimizer.warehouse_optimization_app.modules.product.application.ports.out;

import com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain.Product;

import java.util.List;
import java.util.Optional;

/** Output port: the storage capability the product use cases need. */
public interface ProductRepository {

    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findBySku(String sku);
}