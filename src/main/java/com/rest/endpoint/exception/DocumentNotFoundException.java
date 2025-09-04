package com.rest.endpoint.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DocumentNotFoundException extends RuntimeException {
  public DocumentNotFoundException(Long id) {
    super("Could not find document " + id);
  }
}
