package com.api.testappsynergyway.configuration.exception;

import lombok.NonNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected @NonNull ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                                      HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ExceptionDto(status, ex), status);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionDto> handleException(Exception ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new ExceptionDto(INTERNAL_SERVER_ERROR, ex));
    }

    @ExceptionHandler(value = {EntityExistsException.class,
            EmptyResultDataAccessException.class})
    public ResponseEntity<ExceptionDto> handleBadRequest(Exception ex) {
        return ResponseEntity.status(BAD_REQUEST).body(new ExceptionDto(BAD_REQUEST, ex));
    }


}