package com.kimmoller.iamproject.identityservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class AccountEntity {
  @Id @GeneratedValue private UUID id;

  @NotNull private String username;
  @NotNull private String systemId;

  @NotNull private OffsetDateTime creationTime;

  private OffsetDateTime creationProvisionTime;
  private OffsetDateTime creationCommitTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "identity_id")
  private IdentityEntity identity;
}
