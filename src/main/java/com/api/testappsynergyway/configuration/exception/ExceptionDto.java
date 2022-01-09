package com.api.testappsynergyway.configuration.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
class ExceptionDto {

    UUID id = randomUUID();
    LocalDateTime timestamp = now();
    HttpStatus status;
    String error;
    @JsonInclude(NON_NULL)
    String message;

    public ExceptionDto(HttpStatus status, Exception exception) {
        this.message = exception.getMessage();
        fillCommonInfo(exception, status);
    }

    private void fillCommonInfo(Exception exception, HttpStatus status) {
        this.status = status;
        this.error = exception.getClass().getSimpleName();
    }



}
