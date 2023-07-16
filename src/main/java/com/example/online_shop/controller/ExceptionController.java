package com.example.online_shop.controller;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Error> getAccessError(RuntimeException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(showErrorMessage((exception)));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> getNotFoundError(RuntimeException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(showErrorMessage(exception));
    }

    private Error showErrorMessage(RuntimeException exception) {
        return new Error(exception.getMessage());
    }

    private record Error(String errorMessage) {
    }
}
