package com.example.ConsulteerZadatak.exception;

import com.example.ConsulteerZadatak.exception.model.ErrorCode;
import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException{
    public BadRequestException(String message) {
        super(message, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
    }
}
