package com.kimmoller.iamproject.mqschema.dto;

import jakarta.validation.constraints.NotNull;
import com.kimmoller.iamproject.apischema.dto.account.AccountDto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccountLifecycleEventDto {
  @NotNull private AccountDto accountDto;
  @NotNull private UUID identityId;
  @NotNull private AccountLifecycleEventType accountLifecycleEventType;
}
