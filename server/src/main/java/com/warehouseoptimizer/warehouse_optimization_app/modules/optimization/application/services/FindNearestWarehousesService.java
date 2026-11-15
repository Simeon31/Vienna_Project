package com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.application.services;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.ports.out.StoreRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain.Store;
import com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.application.dtos.WarehouseStorePair;
import com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.application.ports.in.FindNearestWarehousesUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.domain.GeoDistance;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.application.ports.out.WarehouseRepository;
import com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.domain.Warehouse;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class FindNearestWarehousesService implements FindNearestWarehousesUseCase {

    private final StoreRepository storeRepository;
    private final WarehouseRepository warehouseRepository;

    public FindNearestWarehousesService(
            StoreRepository storeRepository,
            WarehouseRepository warehouseRepository) {
        this.storeRepository = storeRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<WarehouseStorePair> findNearest(Long storeId, int limit) {
        if (limit < 1) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found: " + storeId));

        return warehouseRepository.findAll().stream()
                .filter(Warehouse::active)
                .map(warehouse -> new Candidate(
                        warehouse,
                        GeoDistance.haversineKm(
                                store.latitude(),
                                store.longitude(),
                                warehouse.latitude(),
                                warehouse.longitude())))
                .sorted(Comparator.comparingDouble(Candidate::distanceKm)
                        .thenComparing(candidate -> candidate.warehouse().id()))
                .limit(limit)
                .map(new CandidateMapper(store.id()))
                .toList();
    }

    private record Candidate(Warehouse warehouse, double distanceKm) {
    }

    private static final class CandidateMapper implements java.util.function.Function<Candidate, WarehouseStorePair> {
        private final Long storeId;
        private int rank;

        private CandidateMapper(Long storeId) {
            this.storeId = storeId;
        }

        @Override
        public WarehouseStorePair apply(Candidate candidate) {
            return new WarehouseStorePair(storeId, candidate.warehouse().id(), ++rank, candidate.distanceKm());
        }
    }
}
