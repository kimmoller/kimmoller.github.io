package com.kimmoller.iamproject.identityservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kimmoller.iamproject.identityservice.entity.IdentityEntity;
import java.util.UUID;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IdentityRepositoryTest extends RepositoryTest {
  @Autowired IdentityRepository identityRepository;

  private static final String FIRST_NAME = "John";
  private static final String LAST_NAME = "Smith";
  private static final String EMAIL = "john.smith@example.org";

  UUID identityId;

  @Test
  @Order(1)
  void whenSaveIdentity_withValidData_returnSavedIdentity() {
    var identityEntity =
        IdentityEntity.builder().firstName(FIRST_NAME).lastName(LAST_NAME).email(EMAIL).build();
    var savedEntity = identityRepository.save(identityEntity);
    assertEquals(FIRST_NAME, savedEntity.getFirstName());
    assertEquals(LAST_NAME, savedEntity.getLastName());
    assertEquals(EMAIL, savedEntity.getEmail());
    identityId = savedEntity.getId();
  }

  @Test
  @Order(2)
  void whenSaveIdentity_withNonUniqueEmail_throwError() {
    var identityEntity =
        IdentityEntity.builder().firstName(FIRST_NAME).lastName(LAST_NAME).email(EMAIL).build();
    assertThrows(
        DataIntegrityViolationException.class, () -> identityRepository.save(identityEntity));
  }

  @Test
  @Order(3)
  void whenUpdateIdentity_withValidData_returnUpdatedUser() {
    var existingIdentity = identityRepository.findById(identityId).get();
    assertEquals(EMAIL, existingIdentity.getEmail());
    existingIdentity.setEmail("updateEmail@example.org");
    assertEquals("updateEmail@example.org", existingIdentity.getEmail());
  }

  @Test
  @Order(4)
  void whenDeleteIdentity_identityNoLongerFoundFromRepository() {
    var existingIdentity = identityRepository.findById(identityId);
    assertTrue(existingIdentity.isPresent());
    identityRepository.delete(existingIdentity.get());

    var nonExistingIdentity = identityRepository.findById(identityId);
    assertTrue(nonExistingIdentity.isEmpty());
  }

  @Test
  @Order(5)
  void whenSaveIdentity_withOnlyNames_returnSavedIdentity() {
    var identityEntity = IdentityEntity.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();
    var savedEntity = identityRepository.save(identityEntity);
    assertEquals(FIRST_NAME, savedEntity.getFirstName());
    assertEquals(LAST_NAME, savedEntity.getLastName());
  }
}
