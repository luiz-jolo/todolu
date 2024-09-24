package com.example.todolu.infra.exception;

import com.example.todolu.domain.taskcard.TaskCardStatus;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String errorMessage = String.format("Invalid value '%s' for parameter '%s'. Allowed values are: %s",
                ex.getValue(),
                ex.getName(),
                Stream.of(TaskCardStatus.values()).map(Enum::name).collect(Collectors.joining(", ")));

        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException invalidFormatException) {
            String targetTypeName = invalidFormatException.getTargetType().getSimpleName();
            if (Enum.class.isAssignableFrom(invalidFormatException.getTargetType())) {
                String enumValues = Stream.of(TaskCardStatus.values())
                        .map(Enum::name)
                        .collect(Collectors.joining(", "));
                String errorMessage = String.format("Invalid value '%s' for enum %s. Allowed values are: %s",
                        invalidFormatException.getValue(),
                        targetTypeName,
                        enumValues);
                return ResponseEntity.badRequest().body(errorMessage);
            }
        }

        return ResponseEntity.badRequest().body("Malformed JSON request");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public record ErrorFieldData(String field, String message){
        public ErrorFieldData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
