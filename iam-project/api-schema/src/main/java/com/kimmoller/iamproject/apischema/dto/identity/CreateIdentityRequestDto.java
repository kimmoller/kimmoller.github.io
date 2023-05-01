package com.kimmoller.iamproject.apischema.dto.identity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "CreateIdentityRequestDto")
public class CreateIdentityRequestDto {
  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull private String firstName;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull private String lastName;

  private String email;
}
