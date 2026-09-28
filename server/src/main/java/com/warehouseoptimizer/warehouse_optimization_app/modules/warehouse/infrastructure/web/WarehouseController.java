package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.in.ManageWarehouseUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.Warehouse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final ManageWarehouseUseCase manageWarehouseUseCase;

    public WarehouseController(ManageWarehouseUseCase manageWarehouseUseCase) {
        this.manageWarehouseUseCase = manageWarehouseUseCase;
    }

    @GetMapping
    public List<WarehouseResponse> getAllWarehouses() {
        return manageWarehouseUseCase.getAllWarehouses().stream()
                .map(this::toResponse)
                .toList();
    }

    @PostMapping
    public ResponseEntity<WarehouseResponse> createWarehouse(@RequestBody CreateWarehouseRequest request) {
        Warehouse created = manageWarehouseUseCase.createWarehouse(
                request.name(),
                request.latitude(),
                request.longitude(),
                request.capacity());
        return ResponseEntity.ok(toResponse(created));
    }

    private WarehouseResponse toResponse(Warehouse warehouse) {
        return new WarehouseResponse(
                warehouse.id(),
                warehouse.name(),
                warehouse.latitude(),
                warehouse.longitude(),
                warehouse.capacity(),
                warehouse.active());
    }

    public record CreateWarehouseRequest(
            String name,
            double latitude,
            double longitude,
            double capacity) {
    }

    public record WarehouseResponse(
            Long id,
            String name,
            double latitude,
            double longitude,
            double capacity,
            boolean active) {
    }
}