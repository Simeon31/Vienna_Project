package com.warehouseoptimizer.warehouse_optimization_app;

import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.annotation.ImportAwareAotBeanPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

/**
 * {@link ApplicationContextInitializer} to restore an application context based on previous AOT processing.
 */
@Generated
public class WarehouseOptimizationApplicationTests__TestContext001_ApplicationContextInitializer implements ApplicationContextInitializer<GenericApplicationContext> {
  @Override
  public void initialize(GenericApplicationContext applicationContext) {
    DefaultListableBeanFactory beanFactory = applicationContext.getDefaultListableBeanFactory();
    beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
    beanFactory.setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE);
    addImportAwareBeanPostProcessors(beanFactory);
    new WarehouseOptimizationApplicationTests__TestContext001_BeanFactoryRegistrations().registerBeanDefinitions(beanFactory);
    new WarehouseOptimizationApplicationTests__TestContext001_BeanFactoryRegistrations().registerAliases(beanFactory);
  }

  /**
   * Add ImportAwareBeanPostProcessor to support ImportAware beans.
   */
  private void addImportAwareBeanPostProcessors(DefaultListableBeanFactory beanFactory) {
    Map<String, String> mappings = new HashMap<>();
    mappings.put("org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration", "org.springframework.boot.security.autoconfigure.web.servlet.ServletWebSecurityAutoConfiguration$EnableWebSecurityConfiguration");
    mappings.put("org.springframework.session.jdbc.config.annotation.web.http.JdbcHttpSessionConfiguration", "org.springframework.boot.session.jdbc.autoconfigure.JdbcSessionAutoConfiguration");
    mappings.put("org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration", "org.springframework.boot.transaction.autoconfigure.TransactionAutoConfiguration$EnableTransactionManagementConfiguration$CglibAutoProxyConfiguration");
    mappings.put("org.springframework.scheduling.annotation.ProxyAsyncConfiguration", "org.springframework.modulith.events.config.EventPublicationAutoConfiguration$AsyncEnablingConfiguration");
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ImportAwareAotBeanPostProcessor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(() -> new ImportAwareAotBeanPostProcessor(mappings));
    beanFactory.registerBeanDefinition("org.springframework.context.annotation.internalImportAwareAotProcessor", beanDefinition);
  }
}
