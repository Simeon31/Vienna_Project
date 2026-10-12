package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.application.dtos.StoreResponse;
import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreResponseMapper {

    @Mapping(target = "id", expression = "java(store.id())")
    @Mapping(target = "name", expression = "java(store.name())")
    @Mapping(target = "address", expression = "java(store.address())")
    @Mapping(target = "city", expression = "java(store.city())")
    @Mapping(target = "warehouseId", expression = "java(store.warehouseId())")
    @Mapping(target = "capacityM3", expression = "java(store.capacityM3())")
    StoreResponse toResponse(Store store);
}
