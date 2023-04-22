package com.kimmoller.iamproject.identityservice.repository;

import com.kimmoller.iamproject.identityservice.configuration.FlywayConfig;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = FlywayConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class RepositoryTest {
  @Autowired Flyway flyway;

  @BeforeAll
  public void init() {
    flyway.clean();
    flyway.migrate();
  }

  @AfterAll
  void clean() {
    flyway.clean();
    flyway.migrate();
  }
}
