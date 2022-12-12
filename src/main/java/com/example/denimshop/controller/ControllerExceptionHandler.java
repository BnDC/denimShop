package com.example.denimshop.controller;

import com.example.denimshop.exception.DuplicatedEmailException;
import com.example.denimshop.exception.UserNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> jwtExceptionHandler(JwtException e) {
        return new ResponseEntity<>(e.getMessage(), UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<String> duplicatedEmailExceptionHandler(DuplicatedEmailException e) {
        return new ResponseEntity<>(e.getMessage(), UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<String> userNotFoundHandler(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), UNAUTHORIZED);
    }


}
