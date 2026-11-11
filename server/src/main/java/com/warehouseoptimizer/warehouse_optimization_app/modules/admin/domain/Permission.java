package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain;

public class Permission {

    private final Integer id;
    private final String name;

    public Permission(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}