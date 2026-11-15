package com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.application.dtos.WarehouseStorePair;
import com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.application.ports.in.FindNearestWarehousesUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class FindNearestWarehousesUseCaseAdapter {

    private final FindNearestWarehousesUseCase findNearestWarehousesUseCase;

    public FindNearestWarehousesUseCaseAdapter(FindNearestWarehousesUseCase findNearestWarehousesUseCase) {
        this.findNearestWarehousesUseCase = findNearestWarehousesUseCase;
    }

    @GetMapping("/{storeId}/warehouse-recommendations")
    public List<WarehouseStorePair> findNearest(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "3") int limit) {
        return findNearestWarehousesUseCase.findNearest(storeId, limit);
    }
}
