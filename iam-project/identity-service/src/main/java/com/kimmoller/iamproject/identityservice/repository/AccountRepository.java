package com.kimmoller.iamproject.identityservice.repository;

import com.kimmoller.iamproject.identityservice.entity.AccountEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
  Optional<AccountEntity> findAccountByIdentityIdAndSystemId(UUID identityId, String systemId);
}
