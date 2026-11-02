package org.springframework.modulith.moments.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link MomentsJacksonAutoConfiguration}.
 */
@Generated
public class MomentsJacksonAutoConfiguration__TestContext001_BeanDefinitions {
  /**
   * Get the bean definition for 'momentsJacksonAutoConfiguration'.
   */
  public static BeanDefinition getMomentsJacksonAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MomentsJacksonAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(MomentsJacksonAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'momentsJacksonModule'.
   */
  private static BeanInstanceSupplier<MomentsJacksonAutoConfiguration.MomentsJacksonModule> getMomentsJacksonModuleInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MomentsJacksonAutoConfiguration.MomentsJacksonModule>forFactoryMethod(MomentsJacksonAutoConfiguration.class, "momentsJacksonModule")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("org.springframework.modulith.moments.autoconfigure.MomentsJacksonAutoConfiguration", MomentsJacksonAutoConfiguration.class).momentsJacksonModule());
  }

  /**
   * Get the bean definition for 'momentsJacksonModule'.
   */
  public static BeanDefinition getMomentsJacksonModuleBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MomentsJacksonAutoConfiguration.MomentsJacksonModule.class);
    beanDefinition.setFactoryBeanName("org.springframework.modulith.moments.autoconfigure.MomentsJacksonAutoConfiguration");
    beanDefinition.setInstanceSupplier(getMomentsJacksonModuleInstanceSupplier());
    return beanDefinition;
  }
}
