package com.kimmoller.iamproject.identityservice.service;

import com.kimmoller.iamproject.apischema.dto.identity.CreateIdentityRequestDto;
import com.kimmoller.iamproject.apischema.dto.identity.IdentityDto;
import com.kimmoller.iamproject.apischema.dto.identity.PatchIdentityDto;
import com.kimmoller.iamproject.identityservice.entity.IdentityEntity;
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
public class IdentityService {
  private final IdentityRepository identityRepository;

  /**
   * Create new identity with given request.
   *
   * @param createIdentityRequestDto Create request dto
   * @return IdentityDto
   */
  public IdentityDto createIdentity(CreateIdentityRequestDto createIdentityRequestDto) {
    log.info(
        "Creating new identity {} {}",
        createIdentityRequestDto.getFirstName(),
        createIdentityRequestDto.getLastName());
    var identityEntity =
        IdentityEntity.builder()
            .firstName(createIdentityRequestDto.getFirstName())
            .lastName(createIdentityRequestDto.getLastName())
            .email(createIdentityRequestDto.getEmail())
            .build();
    var savedEntity = identityRepository.save(identityEntity);
    log.info("Created new identity {} {}", savedEntity.getFirstName(), savedEntity.getLastName());
    return IdentityMapper.map(savedEntity);
  }

  /**
   * Patch identity with given username and patch request.
   *
   * @param id Identity ID
   * @param patchIdentityDto Patch request dto
   * @return IdentityDto
   */
  public IdentityDto patchIdentity(UUID id, PatchIdentityDto patchIdentityDto)
      throws EntityNotFoundException {
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
