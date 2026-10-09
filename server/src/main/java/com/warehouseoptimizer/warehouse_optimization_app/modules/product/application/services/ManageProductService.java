package com.warehouseoptimizer.warehouse_optimization_app.modules.product.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.product.application.ports.in.ManageProductUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.product.application.ports.out.ProductRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain.Product;
import com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain.ProductNotFoundException;
import com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain.ProductSkuAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageProductService implements ManageProductUseCase {

    private final ProductRepository repository;

    public ManageProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product createProduct(String sku, String name, String category,
                                 double lengthCm, double widthCm, double heightCm, double weightKg) {
        if (repository.findBySku(sku).isPresent()) {
            throw new ProductSkuAlreadyExistsException(sku);
        }
        Product product = new Product(null, sku, name, category, lengthCm, widthCm, heightCm, weightKg);
        return repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product updateProduct(Long id, String sku, String name, String category,
                                 double lengthCm, double widthCm, double heightCm, double weightKg) {
        Product existing = getProductById(id);

        repository.findBySku(sku).ifPresent(other -> {
            if (!other.id().equals(existing.id())) {
                throw new ProductSkuAlreadyExistsException(sku);
            }
        });

        Product updated = new Product(existing.id(), sku, name, category,
                lengthCm, widthCm, heightCm, weightKg);
        return repository.save(updated);
    }
}