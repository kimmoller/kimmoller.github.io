package com.kimmoller.iamproject.identityservice.controller;

import com.kimmoller.iamproject.identityservice.dto.account.AccountDto;
import com.kimmoller.iamproject.identityservice.dto.account.PatchAccountDto;
import com.kimmoller.iamproject.identityservice.service.AccountService;
import java.util.UUID;
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

@Controller
@RequiredArgsConstructor
public class AccountController {
  private final AccountService accountService;

  @GetMapping("/identity/{identityId}/account/{systemId}")
  public ResponseEntity<AccountDto> getAccountWithIdentityIdAndSystemId(
      @PathVariable UUID identityId, @PathVariable String systemId) {
    var account = accountService.getAccountWithIdentityIdAndSystemId(identityId, systemId);
    return new ResponseEntity<>(account, HttpStatus.OK);
  }

  @PostMapping("/identity/{identityId}/account")
  public ResponseEntity<AccountDto> createNewAccount(
      @PathVariable UUID identityId, @RequestBody AccountDto accountDto) {
    var account = accountService.createNewAccount(identityId, accountDto);
    return new ResponseEntity<>(account, HttpStatus.CREATED);
  }

  @PatchMapping("/identity/{identityId}/account/{systemId}")
  public ResponseEntity<AccountDto> patchAccount(
      @PathVariable UUID identityId,
      @PathVariable String systemId,
      @RequestBody PatchAccountDto patchAccountDto) {
    var account = accountService.patchAccount(identityId, systemId, patchAccountDto);
    return new ResponseEntity<>(account, HttpStatus.OK);
  }

  @DeleteMapping("/identity/{identityId}/account/{systemId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAccountWithIdentityIdAndSystemId(
      @PathVariable UUID identityId, @PathVariable String systemId) {
    accountService.deleteAccountWithIdentityIdAndSystemId(identityId, systemId);
  }
}
