package com.warehouseoptimizer.warehouse_optimization_app.modules.product.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.product.application.ports.out.ProductRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain.Product;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository jpaRepository;

    public ProductRepositoryAdapter(ProductJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity saved = jpaRepository.save(toEntity(product));
        return toDomain(saved);
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll(Sort.by("id")).stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return jpaRepository.findBySku(sku).map(this::toDomain);
    }

    private ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity(
                product.sku(),
                product.name(),
                product.category(),
                product.lengthCm(),
                product.widthCm(),
                product.heightCm(),
                product.weightKg());
        if(product.id() != null) {
            entity.setId(product.id());
        }
        return entity;
    }

    private Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getSku(),
                entity.getName(),
                entity.getCategory(),
                entity.getLengthCm(),
                entity.getWidthCm(),
                entity.getHeightCm(),
                entity.getWeightKg());
    }
}
