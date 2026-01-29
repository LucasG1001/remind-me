package com.example.remindme.infrastructure.config;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.remindme.domain.shared.exceptions.BusinessException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ValidationErrorData>> handleValidationErrors(MethodArgumentNotValidException ex) {
    var errors = ex.getFieldErrors().stream()
        .map(ValidationErrorData::new)
        .collect(Collectors.toList());

    return ResponseEntity.badRequest().body(errors);
  }

  public record ValidationErrorData(String field, String message) {
    public ValidationErrorData(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
    var error = new ErrorResponse(ex.getMessage());
    return ResponseEntity.status(400).body(error);
  }

  public record ErrorResponse(
      String message) {
  }
}