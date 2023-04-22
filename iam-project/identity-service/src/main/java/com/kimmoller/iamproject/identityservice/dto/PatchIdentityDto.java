package com.kimmoller.iamproject.identityservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "PatchIdentityDto")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatchIdentityDto {
  @Schema(nullable = true)
  private Optional<String> username;

  @Schema(nullable = true)
  private Optional<String> email;
}
