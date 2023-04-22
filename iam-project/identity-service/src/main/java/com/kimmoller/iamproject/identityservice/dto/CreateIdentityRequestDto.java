package com.kimmoller.iamproject.identityservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "CreateIdentityRequestDto")
public class CreateIdentityRequestDto {
  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull private String username;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull private String email;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull private String password;
}
