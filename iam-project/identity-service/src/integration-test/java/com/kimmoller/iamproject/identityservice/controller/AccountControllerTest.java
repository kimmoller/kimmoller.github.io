package com.kimmoller.iamproject.identityservice.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kimmoller.iamproject.identityservice.dto.account.AccountDto;
import com.kimmoller.iamproject.identityservice.dto.account.PatchAccountDto;
import com.kimmoller.iamproject.identityservice.entity.IdentityEntity;
import com.kimmoller.iamproject.identityservice.repository.IdentityRepository;
import io.restassured.http.ContentType;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountControllerTest extends ControllerTest {

  private static final String USERNAME = "testUsername";
  private static final String SYSTEM_ID = "system_x";

  @Autowired IdentityRepository identityRepository;

  IdentityEntity identity;

  @BeforeAll
  void createIdentity() {
    var identityEntity = IdentityEntity.builder().firstName("John").lastName("Smith").build();
    identity = identityRepository.save(identityEntity);
  }

  @Test
  @Order(1)
  void whenCreateAccount_withValidData_returnCreatedAccountDto() {
    var accountDto = AccountDto.builder().username(USERNAME).systemId(SYSTEM_ID).build();
    given()
        .contentType(ContentType.JSON)
        .body(accountDto)
        .post("/identity/" + identity.getId() + "/account")
        .then()
        .expect(status().isCreated())
        .body(
            "username",
            is(USERNAME),
            "systemId",
            is(SYSTEM_ID),
            "creationTime",
            notNullValue(),
            "creationProvisionTime",
            nullValue(),
            "creationCommitTime",
            nullValue());
  }

  @Test
  @Order(2)
  void whenGetAccountWithIdentityIdAndSystemId_andAccountExists_returnAccountDto() {
    given()
        .get("/identity/" + identity.getId() + "/account/" + SYSTEM_ID)
        .then()
        .expect(status().isOk())
        .body("username", is(USERNAME), "systemId", is(SYSTEM_ID));
  }

  @Test
  @Order(3)
  void whenPatchAccount_withProvisionTime_returnAccountDtoWithProvisionTimeNotNull() {
    var patchRequest =
        PatchAccountDto.builder().creationProvisionTime(Optional.of(OffsetDateTime.now())).build();
    given()
        .contentType(ContentType.JSON)
        .body(patchRequest)
        .patch("identity/" + identity.getId() + "/account/" + SYSTEM_ID)
        .then()
        .expect(status().isOk())
        .body("creationProvisionTime", notNullValue(), "creationCommitTime", nullValue());
  }

  @Test
  @Order(4)
  void whenPatchAccount_withCommitTime_returnAccountDtoWithAllTimesSet() {
    var patchRequest =
        PatchAccountDto.builder().creationCommitTime(Optional.of(OffsetDateTime.now())).build();
    given()
        .contentType(ContentType.JSON)
        .body(patchRequest)
        .patch("identity/" + identity.getId() + "/account/" + SYSTEM_ID)
        .then()
        .expect(status().isOk())
        .body("creationProvisionTime", notNullValue(), "creationCommitTime", notNullValue());
  }

  @Test
  @Order(5)
  void whenDeleteAccountWithIdentityIdAndSystemId_accountDeleted() {
    given()
        .delete("identity/" + identity.getId() + "/account/" + SYSTEM_ID)
        .then()
        .expect(status().isNoContent());
  }
}
