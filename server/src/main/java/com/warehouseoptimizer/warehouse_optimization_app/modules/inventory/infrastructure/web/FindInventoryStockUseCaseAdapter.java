package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.dtos.StockView;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.in.FindInventoryStockUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Demonstration HTTP adapter; delegates all stock work to the input port. */
@RestController
@RequestMapping("/inventory")
public class FindInventoryStockUseCaseAdapter {

    private final FindInventoryStockUseCase findInventoryStockUseCase;

    public FindInventoryStockUseCaseAdapter(FindInventoryStockUseCase findInventoryStockUseCase) {
        this.findInventoryStockUseCase = findInventoryStockUseCase;
    }

    @GetMapping("/warehouses/{warehouseId}/items/{sku}")
    public ResponseEntity<StockView> findStock(
            @PathVariable("warehouseId") String warehouseId,
            @PathVariable("sku") String sku) {
        return ResponseEntity.of(findInventoryStockUseCase.findStock(sku, warehouseId));
    }
}
