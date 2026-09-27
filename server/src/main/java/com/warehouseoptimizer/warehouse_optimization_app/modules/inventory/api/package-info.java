/**
 * Public contracts owned by inventory and intended for other backend modules.
 * Consumers depend only on shared_interfaces and shared_dtos in this package.
 * Domain objects, repository ports, and persistence details stay internal.
 *
 * <p>Example: a planning module can query available units for a SKU in a warehouse.
 * This snapshot is informational; it does not reserve stock or guarantee a later allocation.
 */
@org.springframework.modulith.NamedInterface("api")
package com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.api;
