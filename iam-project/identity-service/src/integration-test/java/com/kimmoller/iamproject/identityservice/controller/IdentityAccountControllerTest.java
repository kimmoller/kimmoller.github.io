package com.kimmoller.iamproject.identityservice.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kimmoller.iamproject.identityservice.dto.AccountDto;
import com.kimmoller.iamproject.identityservice.dto.identity.CreateIdentityRequestDto;
import com.kimmoller.iamproject.identityservice.dto.identity.IdentityDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class IdentityAccountControllerTest extends ControllerTest {

  private static final String FIRST_NAME = "John";
  private static final String LAST_NAME = "Smith";
  private static final String EMAIL = "john.smith@example.org";
  private static final String USERNAME = "testUsername";
  private static final String SYSTEM_ID = "system_x";

  @Test
  void whenGetIdentity_withAccounts_accountDtoIsReturnedInIdentityDto() {
    var createIdentityRequest =
        CreateIdentityRequestDto.builder()
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .email(EMAIL)
            .build();
    var identity =
        given()
            .contentType(ContentType.JSON)
            .body(createIdentityRequest)
            .post("identity")
            .then()
            .expect(status().isCreated())
            .extract()
            .as(IdentityDto.class);

    var accountDto = AccountDto.builder().username(USERNAME).systemId(SYSTEM_ID).build();
    given()
        .contentType(ContentType.JSON)
        .body(accountDto)
        .post("/identity/" + identity.getId() + "/account")
        .then()
        .expect(status().isCreated());

    given()
        .get("identity/" + identity.getId())
        .then()
        .expect(status().isOk())
        .body(
            "firstName",
            is("John"),
            "lastName",
            is("Smith"),
            "email",
            is("john.smith@example.org"),
            "accounts[0].username",
            is("testUsername"),
            "accounts[0].systemId",
            is("system_x"));
  }
}
