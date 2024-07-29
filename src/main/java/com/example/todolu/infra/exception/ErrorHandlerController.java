package com.example.todolu.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandler404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandler400(MethodArgumentNotValidException ex){
        var err = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(err.stream()
                .map(ErrorFieldData::new)
                .toList());
    }

    public record ErrorFieldData(String field, String message){
        public ErrorFieldData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
