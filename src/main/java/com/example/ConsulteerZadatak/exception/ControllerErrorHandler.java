package com.example.ConsulteerZadatak.exception;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler(com.example.ConsulteerZadatak.exception.CustomException.class)
    public ResponseEntity<?> handleCustomException(com.example.ConsulteerZadatak.exception.CustomException exception) {

        com.example.ConsulteerZadatak.exception.Message message = new  com.example.ConsulteerZadatak.exception.Message(exception.getMessage());
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(message), exception.getHttpStatus());
    }
}
