package com.kimmoller.iamproject.identityservice.service;

import com.kimmoller.iamproject.identityservice.dto.CreateIdentityRequestDto;
import com.kimmoller.iamproject.identityservice.dto.IdentityDto;
import com.kimmoller.iamproject.identityservice.dto.PatchIdentityDto;
import com.kimmoller.iamproject.identityservice.repository.IdentityRepository;
import com.kimmoller.iamproject.identityservice.utils.IdentityMapper;
import com.kimmoller.iamproject.identityservice.entity.IdentityEntity;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdentityService {
  private final IdentityRepository identityRepository;

  /**
   * Create new identity with given request.
   *
   * @param createIdentityRequestDto Create request dto
   * @return IdentityDto
   */
  public IdentityDto createIdentity(CreateIdentityRequestDto createIdentityRequestDto) {
    log.info("Creating new identity with username {}", createIdentityRequestDto.getUsername());
    var identityEntity =
        IdentityEntity.builder()
            .username(createIdentityRequestDto.getUsername())
            .email(createIdentityRequestDto.getEmail())
            .password(createIdentityRequestDto.getPassword())
            .build();
    var savedEntity = identityRepository.save(identityEntity);
    log.info("Created new identity with username {}", savedEntity.getUsername());
    return IdentityMapper.map(savedEntity);
  }

  /**
   * Patch identity with given username and patch request.
   *
   * @param id Identity ID
   * @param patchIdentityDto Patch request dto
   * @return IdentityDto
   */
  public IdentityDto patchIdentity(UUID id, PatchIdentityDto patchIdentityDto) throws EntityNotFoundException {
    log.info("Applying patch for identity {}", id);
    var existingIdentity =
        identityRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(String.format("No identity found with id %s", id)));
    IdentityMapper.applyPatchToIdentityEntity(patchIdentityDto, existingIdentity);
    var updatedIdentity = identityRepository.save(existingIdentity);
    log.info("Successfully patched identity {}", updatedIdentity.getId());
    return IdentityMapper.map(updatedIdentity);
  }

  /**
   * Delete identity by username.
   *
   * @param id Identity ID
   */
  public void deleteIdentity(UUID id) {
    log.info("Deleting identity {}", id);
    identityRepository.findById(id).ifPresent(identityRepository::delete);
    log.info("Identity {} deleted", id);
  }

  /**
   * Get identity with id. Throws EntityNotFoundException if the username was not found.
   *
   * @param id Identity ID
   * @return IdentityDto
   */
  public IdentityDto getIdentityWithId(UUID id) throws EntityNotFoundException {
    var identity =
        identityRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(String.format("No identity found with id %s", id)));
    return IdentityMapper.map(identity);
  }
}
