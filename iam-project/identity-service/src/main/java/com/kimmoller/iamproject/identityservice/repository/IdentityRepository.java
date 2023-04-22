package com.kimmoller.iamproject.identityservice.repository;

import com.kimmoller.iamproject.identityservice.entity.IdentityEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityRepository extends JpaRepository<IdentityEntity, UUID> {}
