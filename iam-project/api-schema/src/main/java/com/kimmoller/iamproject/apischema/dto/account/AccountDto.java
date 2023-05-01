package com.kimmoller.iamproject.apischema.dto.account;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
  private UUID id;
  private String username;
  private String systemId;

  private OffsetDateTime creationTime;
  private OffsetDateTime creationProvisionTime;
  private OffsetDateTime creationCommitTime;
}
