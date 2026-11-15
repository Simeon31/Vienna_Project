package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.dtos.StockView;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.in.FindInventoryStockUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.out.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindInventoryStockService implements FindInventoryStockUseCase {

    private final InventoryRepository repository;

    public FindInventoryStockService(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<StockView> findStock(Long productId, String warehouseId) {
        return repository.findByProductIdAndWarehouseId(productId, warehouseId)
                .map(item -> new StockView(
                        item.productId(),
                        item.warehouseId(),
                        item.demandInUnits(),
                        item.onHand(),
                        item.reserved(),
                        item.availableQuantity()));
    }
}
