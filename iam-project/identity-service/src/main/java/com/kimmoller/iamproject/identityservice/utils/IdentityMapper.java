package com.kimmoller.iamproject.identityservice.utils;

import com.kimmoller.iamproject.identityservice.dto.identity.IdentityDto;
import com.kimmoller.iamproject.identityservice.dto.identity.PatchIdentityDto;
import com.kimmoller.iamproject.identityservice.entity.IdentityEntity;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.springframework.stereotype.Component;

@Component
public class IdentityMapper {
  public static IdentityDto map(IdentityEntity identityEntity) {
    return IdentityDto.builder()
        .id(identityEntity.getId())
        .firstName(identityEntity.getFirstName())
        .lastName(identityEntity.getLastName())
        .email(identityEntity.getEmail())
        .build();
  }

  public static void applyPatchToIdentityEntity(PatchIdentityDto patch, IdentityEntity target) {
    applyPatch(patch::getFirstName, target::setFirstName);
    applyPatch(patch::getLastName, target::setLastName);
    applyPatch(patch::getEmail, target::setEmail);
  }

  /**
   * Apply getter of optional field on a setter of nullable field. If getter value is null, then
   * nothing is patched. If getter value is empty, then setter is applied with null value. If getter
   * value is non empty optional, then setter is applied with the wrapped value.
   *
   * <p>NOTE: we check if the optional supplier returns null, because the the way that spring
   * distinguishes an explicitly null value from missing value, when mapping json.
   */
  private static <T> void applyPatch(Supplier<Optional<T>> getter, Consumer<T> setter) {
    var patch = getter.get();
    if (patch != null) {
      getter.get().ifPresentOrElse(setter::accept, () -> setter.accept(null));
    }
  }
}
