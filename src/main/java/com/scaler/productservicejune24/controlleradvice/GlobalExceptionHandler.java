package com.scaler.productservicejune24.controlleradvice;


import com.scaler.productservicejune24.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
        public ResponseEntity<ExceptionDto>handleArthmeticException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("ArithmeticException has happened");
        exceptionDto.setSolution("I don't know,please try again");
            ResponseEntity<ExceptionDto>reponse=new ResponseEntity<>(
                    exceptionDto,
                    HttpStatus.NOT_FOUND
            );
        return reponse;
        }
    }

