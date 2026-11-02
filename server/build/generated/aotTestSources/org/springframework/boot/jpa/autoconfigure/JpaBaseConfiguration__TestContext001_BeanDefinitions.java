package org.springframework.boot.jpa.autoconfigure;

import java.lang.String;
import java.util.List;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.orm.jpa.persistenceunit.PersistenceManagedTypes;

/**
 * Bean definitions for {@link JpaBaseConfiguration}.
 */
@Generated
public class JpaBaseConfiguration__TestContext001_BeanDefinitions {
  /**
   * Bean definitions for {@link JpaBaseConfiguration.PersistenceManagedTypesConfiguration}.
   */
  @Generated
  public static class PersistenceManagedTypesConfiguration {
    /**
     * Get the bean definition for 'persistenceManagedTypesConfiguration'.
     */
    public static BeanDefinition getPersistenceManagedTypesConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(JpaBaseConfiguration.PersistenceManagedTypesConfiguration.class);
      beanDefinition.setInstanceSupplier(JpaBaseConfiguration.PersistenceManagedTypesConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance for 'persistenceManagedTypes'.
     */
    private static PersistenceManagedTypes getPersistenceManagedTypesInstance() {
      List<String> managedClassNames = List.of("com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence.AccountEntity", "com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence.ManagerAccessEntity", "com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence.RoleEntity", "com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.infrastructure.persistence.InventoryItemEntity", "com.warehouseoptimizer.warehouse_optimization_app.modules.product.infrastructure.persistence.ProductEntity", "com.warehouseoptimizer.warehouse_optimization_app.modules.shipment.infrastructure.persistence.ShipmentEntity", "com.warehouseoptimizer.warehouse_optimization_app.modules.warehouse.infrastructure.persistence.WarehouseEntity", "org.springframework.modulith.events.jpa.updating.DefaultJpaEventPublication");
      List<String> managedPackages = List.of("com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.api", "com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.api.shared_dtos", "com.warehouseoptimizer.warehouse_optimization_app.modules.inventory.api.shared_interfaces", "com.warehouseoptimizer.warehouse_optimization_app.modules.inventory", "org.springframework.modulith.events.jpa.updating");
      return PersistenceManagedTypes.of(managedClassNames, managedPackages);
    }

    /**
     * Get the bean definition for 'persistenceManagedTypes'.
     */
    public static BeanDefinition getPersistenceManagedTypesBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(JpaBaseConfiguration.PersistenceManagedTypesConfiguration.class);
      beanDefinition.setTargetType(PersistenceManagedTypes.class);
      beanDefinition.setPrimary(true);
      beanDefinition.setInstanceSupplier(PersistenceManagedTypesConfiguration::getPersistenceManagedTypesInstance);
      return beanDefinition;
    }
  }
}
