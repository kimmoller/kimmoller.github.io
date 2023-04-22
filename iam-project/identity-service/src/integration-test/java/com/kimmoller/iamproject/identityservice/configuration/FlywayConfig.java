package com.kimmoller.iamproject.identityservice.configuration;

import java.util.Collections;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class FlywayConfig {
  @Bean(initMethod = "start", destroyMethod = "stop")
  public PostgreSQLContainer postgreSQLContainer() {
    return new PostgreSQLContainer("postgres:15.2-alpine")
        .withDatabaseName("postgres")
        .withUsername("postgres")
        .withPassword("postgres");
  }

  @Bean
  public DataSource dataSource(PostgreSQLContainer postgreSQLContainer) {
    return DataSourceBuilder.create()
        .driverClassName("org.postgresql.Driver")
        .url(postgreSQLContainer.getJdbcUrl())
        .username("postgres")
        .password("postgres")
        .build();
  }

  @Bean
  public Flyway flyway(DataSource dataSource) {
    var flyway =
        Flyway.configure()
            .dataSource(dataSource)
            .placeholders(Collections.singletonMap("username", "postgres"));
    flyway.locations("classpath:/db/migration", "classpath:/db/testdata");
    flyway.cleanDisabled(false);
    return flyway.load();
  }
}
