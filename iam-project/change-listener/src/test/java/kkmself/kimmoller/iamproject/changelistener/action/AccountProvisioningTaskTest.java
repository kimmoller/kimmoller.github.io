package kkmself.kimmoller.iamproject.changelistener.action;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kimmoller.iamproject.apischema.dto.account.AccountDto;
import com.kimmoller.iamproject.apischema.dto.account.PatchAccountDto;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kkmself.kimmoller.iamproject.changelistener.HttpApiService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = AccountProvisioningTask.class)
@Slf4j
public class AccountProvisioningTaskTest {
  @MockBean HttpApiService httpApiService;
  @MockBean RabbitTemplate rabbitTemplate;
  @MockBean ObjectMapper beanMapper;
  private ObjectMapper objectMapper;
  private AccountProvisioningTask accountProvisioningTask;

  private static final String IDENTITY_SERVICE_BASE_URL = "http://host.docker.internal:8300";

  @PostConstruct
  private void setupService() {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    accountProvisioningTask =
        new AccountProvisioningTask(rabbitTemplate, objectMapper, httpApiService);
  }

  @Test
  void whenProvisionAccount_withAccountsToBeCreated_thenAccountSentToQueueAndPatched()
      throws IOException {
    var identityId = UUID.randomUUID();
    var mockResponse = getMockResponseForGet(identityId);
    Mockito.when(httpApiService.getRequest(any())).thenReturn(mockResponse);

    Mockito.when(httpApiService.getRequest(any())).thenReturn(mockResponse);
    Mockito.when(httpApiService.patchRequest(any(), any()))
        .thenReturn(getMockResponseForPatch(identityId));
    accountProvisioningTask.provisionAccounts();

    Mockito.verify(httpApiService).getRequest(any());
    Mockito.verify(rabbitTemplate).convertAndSend(anyString(), anyString(), any(Object.class));
    Mockito.verify(httpApiService).patchRequest(any(), any());
  }

  private Response getMockResponseForGet(UUID identityId) throws JsonProcessingException {
    var accountDto =
        AccountDto.builder()
            .id(UUID.randomUUID())
            .creationTime(OffsetDateTime.now())
            .username("testUsername")
            .identityId(identityId)
            .systemId("system-x")
            .build();
    var body = objectMapper.writeValueAsString(List.of(accountDto));
    var mockResponseBody = ResponseBody.create(body, MediaType.parse("JSON"));
    var mockRequest =
        new Request.Builder().url(IDENTITY_SERVICE_BASE_URL + "/account/toBeCreated").build();
    return new Response.Builder()
        .message("")
        .protocol(Protocol.HTTP_1_1)
        .code(200)
        .body(mockResponseBody)
        .request(mockRequest)
        .build();
  }

  private Response getMockResponseForPatch(UUID identityId) throws JsonProcessingException {
    var patchDto =
        PatchAccountDto.builder().creationProvisionTime(Optional.of(OffsetDateTime.now())).build();
    var body = objectMapper.writeValueAsString(List.of(patchDto));
    var mockResponseBody = ResponseBody.create(body, MediaType.parse("JSON"));
    var mockRequest =
        new Request.Builder()
            .url(IDENTITY_SERVICE_BASE_URL + "/identity/" + identityId + "/account/system-x")
            .build();
    return new Response.Builder()
        .message("")
        .protocol(Protocol.HTTP_1_1)
        .code(200)
        .body(mockResponseBody)
        .request(mockRequest)
        .build();
  }
}
