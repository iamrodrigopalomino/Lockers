package com.rodri.lockers.backendlockersproject.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
    String message = ex.getMessage() == null ? "Unexpected error" : ex.getMessage();
    if (message.toLowerCase().contains("not found")) {
      return buildError(HttpStatus.NOT_FOUND, message);
    }
    return buildError(HttpStatus.INTERNAL_SERVER_ERROR, message);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityViolationException ex) {
    return buildError(HttpStatus.CONFLICT, "Data integrity violation");
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Map<String, String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
    return buildError(HttpStatus.BAD_REQUEST, "Invalid parameter: " + ex.getName());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
    return buildError(HttpStatus.BAD_REQUEST, "Validation failed");
  }

  private ResponseEntity<Map<String, String>> buildError(HttpStatus status, String message) {
    Map<String, String> body = new HashMap<>();
    body.put("error", message);
    return ResponseEntity.status(status).body(body);
  }
}
