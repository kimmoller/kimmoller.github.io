package com.kimmoller.iamproject.mqschema.dto;

import com.kimmoller.iamproject.apischema.dto.account.AccountDto;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountLifecycleEventDto {
  @NotNull private AccountDto accountDto;
  @NotNull private UUID identityId;
  @NotNull private AccountLifecycleEventType accountLifecycleEventType;
}
