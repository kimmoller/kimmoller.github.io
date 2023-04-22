package com.kimmoller.iamproject.identityservice.configuration;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {EntityNotFoundException.class})
  protected ResponseEntity<Object> handleEntityNotFound(
      RuntimeException exception, WebRequest webRequest) {
    String bodyOfResponse = "Entity not found";
    return handleExceptionInternal(
        exception, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<Object> handleDataIntegrityViolationException(
      RuntimeException exception, WebRequest webRequest) {
    String bodyOfResponse = "Data conflict";
    return handleExceptionInternal(
        exception, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
  }
}
