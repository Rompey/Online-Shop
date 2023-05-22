package com.example.online_shop.web;

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
    public ResponseEntity<Error> getError(AccessDeniedException accessDeniedException){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(showErrorMessage((accessDeniedException)));
    }

    private Error showErrorMessage(AccessDeniedException accessDeniedException) {
        return new Error(accessDeniedException.getMessage());
    }

    private record Error(String errorMessage) {
    }
}
