package com.kimmoller.iamproject.identityservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.kimmoller.iamproject.identityservice.entity.AccountEntity;
import com.kimmoller.iamproject.identityservice.entity.IdentityEntity;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountRepositoryTest extends RepositoryTest {
  @Autowired AccountRepository accountRepository;
  @Autowired IdentityRepository identityRepository;

  private static final String USERNAME = "testUsername";
  private static final String SYSTEM_ID = "system_x";

  IdentityEntity identity;

  @BeforeAll
  void createIdentity() {
    var identityEntity = IdentityEntity.builder().firstName("John").lastName("Smith").build();
    identity = identityRepository.save(identityEntity);
  }

  @Test
  @Order(1)
  void whenCreateAccount_withValidData_thenAccountReturned() {
    var accountEntity =
        AccountEntity.builder()
            .username(USERNAME)
            .systemId(SYSTEM_ID)
            .identity(identity)
            .creationTime(OffsetDateTime.now())
            .build();
    var savedAccount = accountRepository.save(accountEntity);
    assertEquals(USERNAME, savedAccount.getUsername());
    assertEquals(SYSTEM_ID, savedAccount.getSystemId());
    assertEquals(identity.getId(), savedAccount.getIdentity().getId());
    assertNotNull(savedAccount.getCreationTime());
  }

  @Test
  @Order(2)
  void whenCreateAccount_withConflictingUsernameSystemIdPair_thenThrowError() {
    var accountEntity =
        AccountEntity.builder()
            .username(USERNAME)
            .systemId(SYSTEM_ID)
            .identity(identity)
            .creationTime(OffsetDateTime.now())
            .build();
    assertThrows(
        DataIntegrityViolationException.class, () -> accountRepository.save(accountEntity));
  }

  @Test
  @Order(3)
  void whenCreateAccount_withConflictingIdentityIdSystemIdPair_thenThrowError() {
    var accountEntity =
        AccountEntity.builder()
            .username("newUsername")
            .systemId(SYSTEM_ID)
            .identity(identity)
            .creationTime(OffsetDateTime.now())
            .build();
    assertThrows(
        DataIntegrityViolationException.class, () -> accountRepository.save(accountEntity));
  }
}
