package com.kimmoller.iamproject.identityservice.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimmoller.iamproject.identityservice.dto.CreateIdentityRequestDto;
import com.kimmoller.iamproject.identityservice.repository.IdentityRepository;
import com.kimmoller.iamproject.identityservice.dto.IdentityDto;
import com.kimmoller.iamproject.identityservice.dto.PatchIdentityDto;
import com.kimmoller.iamproject.identityservice.entity.IdentityEntity;
import io.restassured.http.ContentType;
import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class IdentityControllerTest extends ControllerTest {

  private static final String USERNAME = "testUsername";
  private static final String EMAIL = "test.user@example.org";
  private static final String PASSWORD = "testPassword";

  @Autowired ObjectMapper objectMapper;
  @Autowired
  IdentityRepository identityRepository;

  @Nested
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  class SuccessfulRequests extends ControllerTest {
    private UUID identityId;

    @Test
    @Order(1)
    void whenCreateIdentity_withValidData_returnCreatedIdentity() {
      var createIdentityRequest =
          CreateIdentityRequestDto.builder()
              .username(USERNAME)
              .email(EMAIL)
              .password(PASSWORD)
              .build();
      var identity =
          given()
              .contentType(ContentType.JSON)
              .body(createIdentityRequest)
              .post("identity")
              .then()
              .expect(status().isCreated())
              .body("username", is("testUsername"), "email", is("test.user@example.org"))
              .extract()
              .as(IdentityDto.class);
      log.info(identity.toString());
      identityId = identity.getId();
    }

    @Test
    @Order(2)
    void whenGetIdentity_andIdentityFound_returnIdentityDto() {
      when()
          .get("identity/" + identityId)
          .then()
          .expect(status().isOk())
          .body("username", is("testUsername"), "email", is("test.user@example.org"));
    }

    @Test
    @Order(3)
    void whenUpdateIdentity_withOnlyUsernameInRequest_otherDataIsKeptIntact() {
      var patchIdentityDto =
          PatchIdentityDto.builder().username(Optional.of("patchedUsername")).build();
      given()
          .contentType(ContentType.JSON)
          .body(patchIdentityDto)
          .patch("identity/" + identityId)
          .then()
          .expect(status().isOk())
          .body("username", is("patchedUsername"), "email", is("test.user@example.org"));
    }

    @Test
    @Order(4)
    void whenUpdateIdentity_withAllFieldsInRequest_allFieldsUpdated() {
      var patchIdentityDto =
          PatchIdentityDto.builder()
              .username(Optional.of("updatedUsername"))
              .email(Optional.of("updated.user@example.org"))
              .password(Optional.of("patchedPassword"))
              .build();
      given()
          .contentType(ContentType.JSON)
          .body(patchIdentityDto)
          .patch("identity/" + identityId)
          .then()
          .expect(status().isOk())
          .body("username", is("updatedUsername"), "email", is("updated.user@example.org"));

      // Fetch identity from repository to verify password is patched as password is not returned in
      // the DTO.
      var patchedIdentity = identityRepository.findById(identityId).orElseThrow();
      assertEquals("patchedPassword", patchedIdentity.getPassword());
    }

    @Test
    @Order(5)
    void whenDeleteIdentity_withExistingUsername_identityNoLongerFound() {
      when().delete("identity/" + identityId).then().expect(status().isNoContent());
      var deletedIdentity = identityRepository.findById(identityId);
      assertTrue(deletedIdentity.isEmpty());
    }
  }

  @Nested
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  class ErrorRequests extends ControllerTest {
    private UUID identityId;

    @BeforeAll
    void initiateData() {
      var identityEntity =
          IdentityEntity.builder().username(USERNAME).email(EMAIL).password(PASSWORD).build();
      var identity = identityRepository.save(identityEntity);
      identityId = identity.getId();

      var identityEntityWithTakenUsername =
          IdentityEntity.builder()
              .username("takenUsername")
              .email("taken.email@example.org")
              .password(PASSWORD)
              .build();
      identityRepository.save(identityEntityWithTakenUsername);
    }

    @Test
    void whenCreateIdentity_withFieldsMissing_return403BadRequest() {
      var createIdentityRequest =
          CreateIdentityRequestDto.builder()
              .username("someUsername")
              .email("some.user@example.org")
              .build();
      given()
          .contentType(ContentType.JSON)
          .body(createIdentityRequest)
          .post("identity")
          .then()
          .expect(status().isBadRequest());
    }

    @Test
    void whenCreateIdentity_withExistingUsername_return409Conflict() {
      var createIdentityRequest =
          CreateIdentityRequestDto.builder()
              .username(USERNAME)
              .email(EMAIL)
              .password(PASSWORD)
              .build();
      given()
          .contentType(ContentType.JSON)
          .body(createIdentityRequest)
          .post("identity")
          .then()
          .expect(status().isConflict());
    }

    @Test
    void whenGetIdentity_withIdentityNotFound_return404NotFound() {
      when().get("identity/" + UUID.randomUUID()).then().expect(status().isNotFound());
    }

    @Test
    void whenUpdateIdentityUsername_withValueThatAlreadyExists_return409Conflict() {
      var patchIdentityDto =
          PatchIdentityDto.builder().username(Optional.of("takenUsername")).build();
      given()
          .contentType(ContentType.JSON)
          .body(patchIdentityDto)
          .patch("identity/" + identityId)
          .then()
          .expect(status().isConflict());
    }

    @Test
    void whenUpdateIdentityEmail_withValueThatAlreadyExists_return409Conflict() {
      var patchIdentityDto =
          PatchIdentityDto.builder().email(Optional.of("taken.email@example.org")).build();
      given()
          .contentType(ContentType.JSON)
          .body(patchIdentityDto)
          .patch("identity/" + identityId)
          .then()
          .expect(status().isConflict());
    }
  }
}
