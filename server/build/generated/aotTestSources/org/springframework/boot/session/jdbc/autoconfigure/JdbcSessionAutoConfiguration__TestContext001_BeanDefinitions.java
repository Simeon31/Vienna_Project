package org.springframework.boot.session.jdbc.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.session.autoconfigure.SessionTimeout;
import org.springframework.core.ResolvableType;
import org.springframework.session.config.SessionRepositoryCustomizer;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;

/**
 * Bean definitions for {@link JdbcSessionAutoConfiguration}.
 */
@Generated
public class JdbcSessionAutoConfiguration__TestContext001_BeanDefinitions {
  /**
   * Get the bean definition for 'jdbcSessionAutoConfiguration'.
   */
  public static BeanDefinition getJdbcSessionAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JdbcSessionAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(JdbcSessionAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'jdbcSessionDataSourceScriptDatabaseInitializer'.
   */
  private static BeanInstanceSupplier<JdbcSessionDataSourceScriptDatabaseInitializer> getJdbcSessionDataSourceScriptDatabaseInitializerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<JdbcSessionDataSourceScriptDatabaseInitializer>forFactoryMethod(JdbcSessionAutoConfiguration.class, "jdbcSessionDataSourceScriptDatabaseInitializer", ObjectProvider.class, ObjectProvider.class, JdbcSessionProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.session.jdbc.autoconfigure.JdbcSessionAutoConfiguration", JdbcSessionAutoConfiguration.class).jdbcSessionDataSourceScriptDatabaseInitializer(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'jdbcSessionDataSourceScriptDatabaseInitializer'.
   */
  public static BeanDefinition getJdbcSessionDataSourceScriptDatabaseInitializerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JdbcSessionDataSourceScriptDatabaseInitializer.class);
    beanDefinition.setDependsOn("flywayInitializer");
    beanDefinition.setFactoryBeanName("org.springframework.boot.session.jdbc.autoconfigure.JdbcSessionAutoConfiguration");
    beanDefinition.setInstanceSupplier(getJdbcSessionDataSourceScriptDatabaseInitializerInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'springBootSessionRepositoryCustomizer'.
   */
  private static BeanInstanceSupplier<SessionRepositoryCustomizer> getSpringBootSessionRepositoryCustomizerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<SessionRepositoryCustomizer>forFactoryMethod(JdbcSessionAutoConfiguration.class, "springBootSessionRepositoryCustomizer", JdbcSessionProperties.class, SessionTimeout.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.session.jdbc.autoconfigure.JdbcSessionAutoConfiguration", JdbcSessionAutoConfiguration.class).springBootSessionRepositoryCustomizer(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'springBootSessionRepositoryCustomizer'.
   */
  public static BeanDefinition getSpringBootSessionRepositoryCustomizerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SessionRepositoryCustomizer.class);
    beanDefinition.setTargetType(ResolvableType.forClassWithGenerics(SessionRepositoryCustomizer.class, JdbcIndexedSessionRepository.class));
    beanDefinition.setFactoryBeanName("org.springframework.boot.session.jdbc.autoconfigure.JdbcSessionAutoConfiguration");
    beanDefinition.setInstanceSupplier(getSpringBootSessionRepositoryCustomizerInstanceSupplier());
    return beanDefinition;
  }
}
