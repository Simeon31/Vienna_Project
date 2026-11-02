package org.springframework.modulith.events.jpa;

import jakarta.persistence.EntityManager;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.env.Environment;
import org.springframework.modulith.events.core.EventSerializer;

/**
 * Bean definitions for {@link JpaEventPublicationAutoConfiguration}.
 */
@Generated
public class JpaEventPublicationAutoConfiguration__TestContext001_BeanDefinitions {
  /**
   * Get the bean definition for 'jpaEventPublicationAutoConfiguration'.
   */
  public static BeanDefinition getJpaEventPublicationAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JpaEventPublicationAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(JpaEventPublicationAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'jpaEventPublicationRepository'.
   */
  private static BeanInstanceSupplier<JpaEventPublicationRepository> getJpaEventPublicationRepositoryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<JpaEventPublicationRepository>forFactoryMethod(JpaEventPublicationAutoConfiguration.class, "jpaEventPublicationRepository", EntityManager.class, EventSerializer.class, Environment.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.modulith.events.jpa.JpaEventPublicationAutoConfiguration", JpaEventPublicationAutoConfiguration.class).jpaEventPublicationRepository(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'jpaEventPublicationRepository'.
   */
  public static BeanDefinition getJpaEventPublicationRepositoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JpaEventPublicationRepository.class);
    beanDefinition.setFactoryBeanName("org.springframework.modulith.events.jpa.JpaEventPublicationAutoConfiguration");
    beanDefinition.setInstanceSupplier(getJpaEventPublicationRepositoryInstanceSupplier());
    return beanDefinition;
  }
}
