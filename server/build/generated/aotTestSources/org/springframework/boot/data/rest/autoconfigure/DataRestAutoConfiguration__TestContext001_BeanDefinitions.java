package org.springframework.boot.data.rest.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DataRestAutoConfiguration}.
 */
@Generated
public class DataRestAutoConfiguration__TestContext001_BeanDefinitions {
  /**
   * Get the bean definition for 'dataRestAutoConfiguration'.
   */
  public static BeanDefinition getDataRestAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DataRestAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(DataRestAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'springBootRepositoryRestConfigurer'.
   */
  private static BeanInstanceSupplier<SpringBootRepositoryRestConfigurer> getSpringBootRepositoryRestConfigurerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<SpringBootRepositoryRestConfigurer>forFactoryMethod(DataRestAutoConfiguration.class, "springBootRepositoryRestConfigurer", ObjectProvider.class, DataRestProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.data.rest.autoconfigure.DataRestAutoConfiguration", DataRestAutoConfiguration.class).springBootRepositoryRestConfigurer(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'springBootRepositoryRestConfigurer'.
   */
  public static BeanDefinition getSpringBootRepositoryRestConfigurerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SpringBootRepositoryRestConfigurer.class);
    beanDefinition.setFactoryBeanName("org.springframework.boot.data.rest.autoconfigure.DataRestAutoConfiguration");
    beanDefinition.setInstanceSupplier(getSpringBootRepositoryRestConfigurerInstanceSupplier());
    return beanDefinition;
  }
}
