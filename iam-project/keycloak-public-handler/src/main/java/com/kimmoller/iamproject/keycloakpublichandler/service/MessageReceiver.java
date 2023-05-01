package com.kimmoller.iamproject.keycloakpublichandler.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimmoller.iamproject.mqschema.dto.AccountLifecycleEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageReceiver {

  private final ObjectMapper objectMapper;

  public void receiveMessage(String message) throws JsonProcessingException {
    var eventDto = objectMapper.readValue(message, AccountLifecycleEventDto.class);
    log.info("Received event {} for identity {} account {}", eventDto.getAccountLifecycleEventType(), eventDto.getIdentityId(), eventDto.getAccountDto());
  }
}
