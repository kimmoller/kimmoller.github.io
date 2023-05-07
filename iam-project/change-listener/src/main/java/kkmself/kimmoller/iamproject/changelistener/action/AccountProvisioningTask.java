package kkmself.kimmoller.iamproject.changelistener.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimmoller.iamproject.apischema.dto.account.AccountDto;
import com.kimmoller.iamproject.apischema.dto.account.PatchAccountDto;
import com.kimmoller.iamproject.mqschema.dto.AccountLifecycleEventDto;
import com.kimmoller.iamproject.mqschema.dto.AccountLifecycleEventType;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import kkmself.kimmoller.iamproject.changelistener.HttpApiService;
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
  private final HttpApiService httpApiService;

  private static final String IDENTITY_SERVICE_BASE_URL = "http://host.docker.internal:8300";

  public void provisionAccounts() {
    log.info("Run provision account");
    try {
      var accounts = getAccountsToBeCreated();
      accounts.forEach(
          accountDto -> {
            try {
              sendAccountCreationToQueue(accountDto);
              patchAccountProvisionTime(accountDto);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          });
    } catch (IOException exception) {
      log.error(
          "Error while fetching accounts, {}: {}",
          exception.getMessage(),
          exception.getStackTrace());
    }
  }

  private List<AccountDto> getAccountsToBeCreated() throws IOException {
    var response = httpApiService.getRequest(IDENTITY_SERVICE_BASE_URL + "/account/toBeCreated");
    List<AccountDto> accounts =
        objectMapper.readValue(
            response.body().string(), new TypeReference<ArrayList<AccountDto>>() {});
    response.close();
    return accounts;
  }

  private void sendAccountCreationToQueue(AccountDto accountDto) throws JsonProcessingException {
    var accountLifecycleEventDto =
        AccountLifecycleEventDto.builder()
            .accountLifecycleEventType(AccountLifecycleEventType.CREATE)
            .accountDto(accountDto)
            .identityId(accountDto.getIdentityId())
            .build();
    var message = objectMapper.writeValueAsString(accountLifecycleEventDto);
    log.info("Sending message {} to queue", message);
    rabbitTemplate.convertAndSend(
        "keycloak-public-exchange",
        "keycloak.public." + accountDto.getUsername(),
        message);
  }

  private void patchAccountProvisionTime(AccountDto accountDto) throws IOException {
    log.info("Patch identity {} account {} provision time", accountDto.getIdentityId(), accountDto.getSystemId());
    var patchAccountDto =
        PatchAccountDto.builder()
            .creationProvisionTime(Optional.of(OffsetDateTime.now()))
            .build();
    var patchResponse =
        httpApiService.patchRequest(
            IDENTITY_SERVICE_BASE_URL
                + "/identity/"
                + accountDto.getIdentityId()
                + "/account/"
                + accountDto.getSystemId(),
            patchAccountDto);
    patchResponse.close();
    log.info("Identity {} account {} provision time patched", accountDto.getIdentityId(), accountDto.getSystemId());
  }
}
