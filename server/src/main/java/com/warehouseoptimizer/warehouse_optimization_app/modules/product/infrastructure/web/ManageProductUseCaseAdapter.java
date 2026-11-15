package com.warehouseoptimizer.warehouse_optimization_app.modules.product.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.product.application.ports.in.ManageProductUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain.Product;
import com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain.ProductNotFoundException;
import com.warehouseoptimizer.warehouse_optimization_app.modules.product.domain.ProductSkuAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ManageProductUseCaseAdapter {

    private final ManageProductUseCase manageProductUseCase;

    public ManageProductUseCaseAdapter(ManageProductUseCase manageProductUseCase) {
        this.manageProductUseCase = manageProductUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        Product created = manageProductUseCase.createProduct(
                request.sku(),
                request.name(),
                request.category(),
                request.lengthCm(),
                request.widthCm(),
                request.heightCm(),
                request.weightKg());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return manageProductUseCase.getAllProducts().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Product product = manageProductUseCase.getProductById(id);
        return ResponseEntity.ok(toResponse(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                         @RequestBody UpdateProductRequest request) {
        Product updated = manageProductUseCase.updateProduct(
                id,
                request.sku(),
                request.name(),
                request.category(),
                request.lengthCm(),
                request.widthCm(),
                request.heightCm(),
                request.weightKg());
        return ResponseEntity.ok(toResponse(updated));
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.id(),
                product.sku(),
                product.name(),
                product.category(),
                product.lengthCm(),
                product.widthCm(),
                product.heightCm(),
                product.weightKg(),
                product.volumeM3());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProductSkuAlreadyExistsException.class)
    public ResponseEntity<String> handleDuplicateSku(ProductSkuAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleUnreadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request body");
    }

    public record CreateProductRequest(
            String sku,
            String name,
            String category,
            double lengthCm,
            double widthCm,
            double heightCm,
            double weightKg) {
    }

    public record UpdateProductRequest(
            String sku,
            String name,
            String category,
            double lengthCm,
            double widthCm,
            double heightCm,
            double weightKg) {
    }

    public record ProductResponse(
            Long id,
            String sku,
            String name,
            String category,
            double lengthCm,
            double widthCm,
            double heightCm,
            double weightKg,
            double volumeM3) {
    }
}