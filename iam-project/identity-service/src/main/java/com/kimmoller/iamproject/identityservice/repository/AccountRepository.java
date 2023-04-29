package com.kimmoller.iamproject.identityservice.repository;

import com.kimmoller.iamproject.identityservice.entity.AccountEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
  Optional<AccountEntity> findAccountByIdentityIdAndSystemId(UUID identityId, String systemId);

  @Query(
      "select a from account a where a.creationProvisionTime is null and a.creationCommitTime is null")
  List<AccountEntity> findAccountsToBeCreated();
}
