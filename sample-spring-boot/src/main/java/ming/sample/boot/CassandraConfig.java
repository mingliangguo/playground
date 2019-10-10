package ming.sample.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

  @Value("${spring.data.cassandra.keyspace-name}")
  private String cassandraKeyspace;

  @Override
  protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
    return Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(cassandraKeyspace)
      .ifNotExists()
      .with(KeyspaceOption.DURABLE_WRITES, true)
      .withSimpleReplication());
  }

  @Override
  protected String getKeyspaceName() {
    return cassandraKeyspace;
  }

  @Bean
  public CassandraClusterFactoryBean cluster() {
    CassandraClusterFactoryBean bean = super.cluster();
    bean.setJmxReportingEnabled(false);
    return bean;
  }

  }
