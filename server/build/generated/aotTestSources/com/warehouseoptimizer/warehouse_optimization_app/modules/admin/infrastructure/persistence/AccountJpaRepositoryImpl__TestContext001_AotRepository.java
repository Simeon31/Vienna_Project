package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import java.util.Optional;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link AccountJpaRepository}.
 */
@Generated
public class AccountJpaRepositoryImpl__TestContext001_AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public AccountJpaRepositoryImpl__TestContext001_AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link AccountJpaRepository#findByEmail(java.lang.String)}.
   */
  public Optional<AccountEntity> findByEmail(String email) {
    String queryString = "SELECT a FROM AccountEntity a WHERE a.email = :email";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("email", email);

    return Optional.ofNullable((AccountEntity) convertOne(query.getSingleResultOrNull(), false, AccountEntity.class));
  }
}
