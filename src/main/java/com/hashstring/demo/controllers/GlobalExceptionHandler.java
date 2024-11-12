package com.hashstring.demo.controllers;

import com.hashstring.demo.exceptions.NoSuchHashAlgorithmException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchHashAlgorithmException.class)
    public ResponseEntity<String> handleNoSuchHashAlgorithmException(NoSuchHashAlgorithmException ex) {
        return new ResponseEntity<>("Invalid algorithm: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}