package com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.in.ManageWarehouseUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.Warehouse;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.WarehouseNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class ManageWarehouseUseCaseAdapter {

    private final ManageWarehouseUseCase manageWarehouseUseCase;

    public ManageWarehouseUseCaseAdapter(ManageWarehouseUseCase manageWarehouseUseCase) {
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

    @PatchMapping("/{id}/activate")
    public ResponseEntity<WarehouseResponse> activateWarehouse(@PathVariable Long id) {
        Warehouse updated = manageWarehouseUseCase.activateWarehouse(id);
        return ResponseEntity.ok(toResponse(updated));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<WarehouseResponse> deactivateWarehouse(@PathVariable Long id) {
        Warehouse updated = manageWarehouseUseCase.deactivateWarehouse(id);
        return ResponseEntity.ok(toResponse(updated));
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
    @ExceptionHandler(WarehouseNotFoundException.class)
    public ResponseEntity<String> handleNotFound(WarehouseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleUnreadable(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request body");
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseResponse> updateWarehouse(
            @PathVariable Long id,
            @RequestBody UpdateWarehouseRequest request) {
        Warehouse updated = manageWarehouseUseCase.updateWarehouse(
                id,
                request.name(),
                request.latitude(),
                request.longitude(),
                request.capacity());
        return ResponseEntity.ok(toResponse(updated));
    }

    public record UpdateWarehouseRequest(
            String name,
            double latitude,
            double longitude,
            double capacity) {
    }
}