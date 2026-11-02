package org.springframework.boot.data.rest.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DataRestProperties}.
 */
@Generated
public class DataRestProperties__TestContext001_BeanDefinitions {
  /**
   * Get the bean definition for 'dataRestProperties'.
   */
  public static BeanDefinition getDataRestPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DataRestProperties.class);
    beanDefinition.setInstanceSupplier(DataRestProperties::new);
    return beanDefinition;
  }
}
