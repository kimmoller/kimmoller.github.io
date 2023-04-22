package com.kimmoller.iamproject.identityservice.dto.identity;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IdentityDto {
  private UUID id;
  private String firstName;
  private String lastName;
  private String email;
}
