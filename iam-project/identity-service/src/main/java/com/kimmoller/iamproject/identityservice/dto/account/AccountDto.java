package com.kimmoller.iamproject.identityservice.dto.account;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDto {
  private UUID id;
  private String username;
  private String systemId;

  private OffsetDateTime creationTime;
  private OffsetDateTime creationProvisionTime;
  private OffsetDateTime creationCommitTime;
}
