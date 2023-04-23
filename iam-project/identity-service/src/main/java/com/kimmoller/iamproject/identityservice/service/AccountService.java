package com.kimmoller.iamproject.identityservice.service;

import com.kimmoller.iamproject.identityservice.dto.AccountDto;
import com.kimmoller.iamproject.identityservice.entity.AccountEntity;
import com.kimmoller.iamproject.identityservice.repository.AccountRepository;
import com.kimmoller.iamproject.identityservice.repository.IdentityRepository;
import com.kimmoller.iamproject.identityservice.utils.IdentityMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
  private final AccountRepository accountRepository;
  private final IdentityRepository identityRepository;

  /**
   * Get account with identity id and system id.
   *
   * @param identityId Identity ID
   * @param systemId System ID
   * @return Account DTO
   */
  public AccountDto getAccountWithIdentityIdAndSystemId(UUID identityId, String systemId) {
    log.info("Get account {} for identity {}", systemId, identityId);
    var accountEntity =
        accountRepository
            .findAccountByIdentityIdAndSystemId(identityId, systemId)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format(
                            "No %s account found for identity %s", systemId, identityId)));
    return IdentityMapper.map(accountEntity);
  }

  /**
   * Create new account for identity with given account data.
   *
   * @param identityId Identity ID
   * @param accountDto Account create data
   * @return Account DTO
   */
  public AccountDto createNewAccount(UUID identityId, AccountDto accountDto) {
    log.info("Creating account {} for identity {}", accountDto.getSystemId(), identityId);
    var identity =
        identityRepository
            .findById(identityId)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format("Identity %s not found", identityId)));
    var accountEntity =
        AccountEntity.builder()
            .username(accountDto.getUsername())
            .systemId(accountDto.getSystemId())
            .identity(identity)
            .build();
    var savedAccount = accountRepository.save(accountEntity);
    log.info(
        "Account {} created for identity {}",
        savedAccount.getSystemId(),
        savedAccount.getIdentity().getId());
    return IdentityMapper.map(savedAccount);
  }

  /**
   * Delete account with identity id and system id.
   *
   * @param identityId Identity ID
   * @param systemId System ID
   */
  public void deleteAccountWithIdentityIdAndSystemId(UUID identityId, String systemId) {
    log.info("Delete account {} from identity {}", systemId, identityId);
    accountRepository
        .findAccountByIdentityIdAndSystemId(identityId, systemId)
        .ifPresent(accountRepository::delete);
    log.info("Account {} delete from identity {}", systemId, identityId);
  }
}
