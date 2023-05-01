package com.kimmoller.iamproject.changelistener.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimmoller.iamproject.apischema.dto.account.AccountDto;
import com.kimmoller.iamproject.mqschema.dto.AccountLifecycleEventDto;
import com.kimmoller.iamproject.mqschema.dto.AccountLifecycleEventType;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class AccountProvisioningTask {

  private final RabbitTemplate rabbitTemplate;
  private final ObjectMapper objectMapper;

  public void provisionAccounts() {
    log.info("Run provision account");
    var account =
        AccountDto.builder()
            .username("testUsername")
            .id(UUID.randomUUID())
            .systemId("system-x")
            .creationTime(OffsetDateTime.now())
            .build();
    var accounts = List.of(account); // These will be fetched from identity-service

    // Run a task to loop through all accounts and send them to the account creation queue

    accounts.forEach(
        accountDto -> {
          try {
            var accountLifecycleEventDto =
                AccountLifecycleEventDto.builder()
                    .accountLifecycleEventType(AccountLifecycleEventType.CREATE)
                    .accountDto(accountDto)
                    .build();
            var message = objectMapper.writeValueAsString(accountLifecycleEventDto);
            log.info("Sending message {} to queue", message);
            rabbitTemplate.convertAndSend(
                "keycloak-public-exchange", "keycloak.public." + accountDto.getUsername(), message);
          } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
          }
        });
  }
}
