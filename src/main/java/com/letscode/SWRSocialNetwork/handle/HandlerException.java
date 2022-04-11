package com.letscode.SWRSocialNetwork.handle;

import com.letscode.SWRSocialNetwork.exceptions.RebelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

record Error(
        String message,
        String description
){}

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler({RebelNotFoundException.class})
    public ResponseEntity<Error> handleRebelNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("not found", "error"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( (error) -> {
            String fieldName = ( (FieldError) error ).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
