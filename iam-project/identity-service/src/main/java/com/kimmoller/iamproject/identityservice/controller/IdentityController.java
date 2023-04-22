package com.kimmoller.iamproject.identityservice.controller;

import com.kimmoller.iamproject.identityservice.dto.CreateIdentityRequestDto;
import com.kimmoller.iamproject.identityservice.dto.IdentityDto;
import com.kimmoller.iamproject.identityservice.dto.PatchIdentityDto;
import com.kimmoller.iamproject.identityservice.service.IdentityService;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
public class IdentityController {
  private final IdentityService identityService;

  @GetMapping("/identity/{id}")
  public ResponseEntity<IdentityDto> getIdentity(@PathVariable UUID id) {
    var identity = identityService.getIdentityWithId(id);
    return new ResponseEntity<>(identity, HttpStatus.OK);
  }

  @PostMapping("/identity")
  public ResponseEntity<IdentityDto> createIdentity(
      @RequestBody @Valid CreateIdentityRequestDto createIdentityRequestDto) {
    var identity = identityService.createIdentity(createIdentityRequestDto);
    return new ResponseEntity<>(identity, HttpStatus.CREATED);
  }

  @PatchMapping("identity/{id}")
  public ResponseEntity<IdentityDto> patchIdentity(@PathVariable UUID id, @RequestBody PatchIdentityDto patchIdentityDto) {
    var identity = identityService.patchIdentity(id, patchIdentityDto);
    return new ResponseEntity<>(identity, HttpStatus.OK);
  }

  @DeleteMapping("identity/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteIdentity(@PathVariable UUID id) {
    identityService.deleteIdentity(id);
  }
}
