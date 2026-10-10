package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.dtos.CreateStoreRequest;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.dtos.StockView;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.dtos.StoreResponse;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.in.FindInventoryStockUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.in.ManageStoreUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryUseCaseRepositoryAdapter {

    private final ManageStoreUseCase manageStoreUseCase;
    private final FindInventoryStockUseCase findInventoryStockUseCase;
    private final StoreResponseMapper responseMapper;

    public InventoryUseCaseRepositoryAdapter(
            ManageStoreUseCase manageStoreUseCase,
            FindInventoryStockUseCase findInventoryStockUseCase,
            StoreResponseMapper responseMapper) {
        this.manageStoreUseCase = manageStoreUseCase;
        this.findInventoryStockUseCase = findInventoryStockUseCase;
        this.responseMapper = responseMapper;
    }

    @GetMapping
    public List<StoreResponse> getAllInventory() {
        return manageStoreUseCase.getAllStores().stream()
                .map(responseMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponse> getInventoryById(@PathVariable Long id) {
        return manageStoreUseCase.getStoreById(id)
                .map(responseMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StoreResponse> createInventory(@RequestBody CreateStoreRequest request) {
        StoreResponse response = responseMapper.toResponse(manageStoreUseCase.createStore(
                request.name(),
                request.address(),
                request.city(),
                request.warehouseId(),
                request.capacityM3()));

        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(response.id())
                        .toUri())
                .body(response);
    }

    @GetMapping("/warehouses/{warehouseId}/products/{productId}")
    public ResponseEntity<StockView> findStock(
            @PathVariable String warehouseId,
            @PathVariable Long productId) {
        return ResponseEntity.of(findInventoryStockUseCase.findStock(productId, warehouseId));
    }
}
