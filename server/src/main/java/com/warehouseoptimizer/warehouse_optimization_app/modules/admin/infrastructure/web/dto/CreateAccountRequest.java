package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web.dto;

public record CreateAccountRequest (String username, String email, String password, Integer roleId) {}
