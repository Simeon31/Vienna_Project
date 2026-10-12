package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.persistence;

import com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.domain.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreEntityMapper {

    @Mapping(target = "id", expression = "java(store.id())")
    @Mapping(target = "name", expression = "java(store.name())")
    @Mapping(target = "address", expression = "java(store.address())")
    @Mapping(target = "city", expression = "java(store.city())")
    @Mapping(target = "warehouseId", expression = "java(store.warehouseId())")
    @Mapping(target = "capacityM3", expression = "java(store.capacityM3())")
    StoreEntity toEntity(Store store);

    Store toDomain(StoreEntity entity);
}
