package com.kimmoller.iamproject.identityservice.controller;

import com.kimmoller.iamproject.identityservice.configuration.FlywayConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = {FlywayConfig.class})
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTest {
  @Autowired Flyway flyway;
  @Autowired private WebApplicationContext context;

  @BeforeAll
  public void init() {
    flyway.clean();
    flyway.migrate();
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  @AfterAll
  void clean() {
    flyway.clean();
    flyway.migrate();
  }
}
