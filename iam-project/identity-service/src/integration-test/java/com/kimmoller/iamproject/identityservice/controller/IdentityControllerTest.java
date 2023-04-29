package com.kimmoller.iamproject.identityservice.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kimmoller.iamproject.identityservice.dto.identity.CreateIdentityRequestDto;
import com.kimmoller.iamproject.identityservice.dto.identity.IdentityDto;
import com.kimmoller.iamproject.identityservice.dto.identity.PatchIdentityDto;
import com.kimmoller.iamproject.identityservice.entity.IdentityEntity;
import com.kimmoller.iamproject.identityservice.repository.IdentityRepository;
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

  private static final String FIRST_NAME = "John";
  private static final String LAST_NAME = "Smith";
  private static final String EMAIL = "john.smith@example.org";
  @Autowired IdentityRepository identityRepository;

  @Nested
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  class SuccessfulRequests extends ControllerTest {
    private UUID identityId;

    @Test
    @Order(1)
    void whenCreateIdentity_withValidData_returnCreatedIdentity() {
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
              .body(
                  "firstName",
                  is("John"),
                  "lastName",
                  is("Smith"),
                  "email",
                  is("john.smith@example.org"))
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
          .body(
              "firstName",
              is("John"),
              "lastName",
              is("Smith"),
              "email",
              is("john.smith@example.org"));
    }

    @Test
    @Order(3)
    void whenUpdateIdentity_withOnlyLastNameInRequest_otherDataIsKeptIntact() {
      var patchIdentityDto = PatchIdentityDto.builder().lastName(Optional.of("Snow")).build();
      given()
          .contentType(ContentType.JSON)
          .body(patchIdentityDto)
          .patch("identity/" + identityId)
          .then()
          .expect(status().isOk())
          .body(
              "firstName",
              is("John"),
              "lastName",
              is("Snow"),
              "email",
              is("john.smith@example.org"));
    }

    @Test
    @Order(4)
    void whenUpdateIdentity_withAllFieldsInRequest_allFieldsUpdated() {
      var patchIdentityDto =
          PatchIdentityDto.builder()
              .firstName(Optional.of("Gary"))
              .lastName(Optional.of("Guy"))
              .email(Optional.of("gary.guy@example.org"))
              .build();
      given()
          .contentType(ContentType.JSON)
          .body(patchIdentityDto)
          .patch("identity/" + identityId)
          .then()
          .expect(status().isOk())
          .body(
              "firstName", is("Gary"), "lastName", is("Guy"), "email", is("gary.guy@example.org"));
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
          IdentityEntity.builder().firstName(FIRST_NAME).lastName(LAST_NAME).email(EMAIL).build();
      var identity = identityRepository.save(identityEntity);
      identityId = identity.getId();

      var identityWithTakenEmail =
          IdentityEntity.builder()
              .firstName(FIRST_NAME)
              .lastName(LAST_NAME)
              .email("taken.name@example.org")
              .build();
      identityRepository.save(identityWithTakenEmail);
    }

    @Test
    void whenCreateIdentity_withNamesMissing_return403BadRequest() {
      var createIdentityRequest =
          CreateIdentityRequestDto.builder().email("john.smith@example.org").build();
      given()
          .contentType(ContentType.JSON)
          .body(createIdentityRequest)
          .post("identity")
          .then()
          .expect(status().isBadRequest());
    }

    @Test
    void whenCreateIdentity_withExistingEmail_return409Conflict() {
      var createIdentityRequest =
          CreateIdentityRequestDto.builder()
              .firstName(FIRST_NAME)
              .lastName(LAST_NAME)
              .email(EMAIL)
              .email(EMAIL)
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
    void whenUpdateIdentityEmail_withValueThatAlreadyExists_return409Conflict() {
      var patchIdentityDto =
          PatchIdentityDto.builder().email(Optional.of("taken.name@example.org")).build();
      given()
          .contentType(ContentType.JSON)
          .body(patchIdentityDto)
          .patch("identity/" + identityId)
          .then()
          .expect(status().isConflict());
    }
  }
}
