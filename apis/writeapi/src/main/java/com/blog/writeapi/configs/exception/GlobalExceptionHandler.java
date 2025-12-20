package com.blog.writeapi.configs.exception;

import com.blog.writeapi.utils.res.ResponseHttp;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<@NonNull ResponseHttp<Map<String, String>>> handleValidation(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValid Error: {}",  ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseHttp<>(
                errors,
                "Validation failed",
                UUID.randomUUID().toString(),
                errors.size(),
                false,
                OffsetDateTime.now()
        ));
    }


    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<@NonNull ResponseHttp<Object>> handleCircuitBreakerOpen(CallNotPermittedException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ResponseHttp<>(
                        null,
                        "System temporarily overloaded (Circuit Breaker Open).",
                        UUID.randomUUID().toString(),
                        0,
                        false,
                        OffsetDateTime.now()
                ));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<@NonNull ResponseHttp<Object>> handleConstraintViolation(ConstraintViolationException ex) {

        String message = ex.getConstraintViolations()
                .stream()
                .map(violation -> {
                    String property = violation.getPropertyPath().toString();
                    return property + ": " + violation.getMessage();
                })
                .collect(Collectors.joining(" | "));

        ResponseHttp<Object> res = new ResponseHttp<>(
                null,
                message,
                UUID.randomUUID().toString(),
                0,
                false,
                OffsetDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

}
