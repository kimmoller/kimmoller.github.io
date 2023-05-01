package com.kimmoller.iamproject.mqschema.dto;

import com.kimmoller.iamproject.apischema.dto.account.AccountDto;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountLifecycleEventDto {
  @NotNull private AccountDto accountDto;
  @NotNull private UUID identityId;
  @NotNull private AccountLifecycleEventType accountLifecycleEventType;
}
