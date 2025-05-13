package com.mvc.MVCDemo.Advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> handleEmployeeNotFound(ResourceNotFoundException exc){
        APIError error = APIError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exc.getMessage())
                .build();

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }

    //Handles all error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handleAllError(MethodArgumentNotValidException exp){
        List<String> err= exp.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        APIError error = APIError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Errror")
                .suberror(err)
                .build();

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

}
