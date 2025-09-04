package com.rest.endpoint.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rest.endpoint.exception.BadRequestException;
import com.rest.endpoint.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Map<String, String>> handleBadRequest(BadRequestException ex) {
    Map<String, String> response = new HashMap<>();
    response.put("error", ex.getMessage());
    response.put("status", "false");
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException ex) {
    Map<String, String> response = new HashMap<>();
    response.put("error", ex.getMessage());
    response.put("status", "false");
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
}
