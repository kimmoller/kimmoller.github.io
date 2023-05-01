package com.kimmoller.iamproject.apischema.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "PatchAccountDto")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatchAccountDto {
  @Schema(nullable = true)
  private Optional<OffsetDateTime> creationProvisionTime;

  @Schema(nullable = true)
  private Optional<OffsetDateTime> creationCommitTime;
}
