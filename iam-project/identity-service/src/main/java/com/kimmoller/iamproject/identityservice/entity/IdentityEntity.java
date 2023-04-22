package com.kimmoller.iamproject.identityservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "identity")
@NoArgsConstructor
@AllArgsConstructor
public class IdentityEntity implements Serializable {
  @Id @GeneratedValue private UUID id;

  @NotNull private String firstName;
  @NotNull private String lastName;
  private String email;
}
