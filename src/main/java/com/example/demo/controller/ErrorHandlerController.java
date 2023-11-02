package com.example.demo.controller;


import com.example.demo.domain.exception.GameNotFoundException;
import com.example.demo.domain.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler(GameNotFoundException.class)
    public ErrorResponse errorResponseReturn(){
        ErrorResponse errorResponse = new ErrorResponse("Game Not Found");
        return errorResponse;
    }
}
