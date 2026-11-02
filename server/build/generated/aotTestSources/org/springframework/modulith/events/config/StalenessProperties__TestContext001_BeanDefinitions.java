package org.springframework.modulith.events.config;

import java.lang.Class;
import java.lang.String;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RegisteredBean;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.context.properties.ConstructorBound;
import org.springframework.boot.context.properties.bind.BindMethod;

/**
 * Bean definitions for {@link StalenessProperties}.
 */
@Generated
public class StalenessProperties__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance for 'spring.modulith.events.staleness-org.springframework.modulith.events.config.StalenessProperties'.
   */
  private static StalenessProperties getStalenessPropertiesInstance(RegisteredBean registeredBean) {
    BeanFactory beanFactory = registeredBean.getBeanFactory();
    String beanName = registeredBean.getBeanName();
    Class<?> beanClass = registeredBean.getBeanClass();
    return (StalenessProperties) ConstructorBound.from(beanFactory, beanName, beanClass);
  }

  /**
   * Get the bean definition for 'stalenessProperties'.
   */
  public static BeanDefinition getStalenessPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(StalenessProperties.class);
    beanDefinition.setAttribute("org.springframework.boot.context.properties.bind.BindMethod", BindMethod.VALUE_OBJECT);
    beanDefinition.setInstanceSupplier(InstanceSupplier.of(StalenessProperties__TestContext001_BeanDefinitions::getStalenessPropertiesInstance));
    return beanDefinition;
  }
}
