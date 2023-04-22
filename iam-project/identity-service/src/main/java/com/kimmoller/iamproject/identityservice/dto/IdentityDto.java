package com.kimmoller.iamproject.identityservice.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IdentityDto {
  private UUID id;
  private String username;
  private String email;
}
