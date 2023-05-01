package com.kimmoller.iamproject.apischema.dto.identity;

import com.kimmoller.iamproject.apischema.dto.account.AccountDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
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

  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private List<AccountDto> accounts;
}
