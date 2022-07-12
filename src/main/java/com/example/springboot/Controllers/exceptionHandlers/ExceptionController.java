package com.example.springboot.Controllers.exceptionHandlers;

import com.example.springboot.models.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> errorA(MethodArgumentNotValidException exception){
        return new ResponseEntity<>(new ErrorDTO(exception, 400), HttpStatus.BAD_REQUEST);
    }
}
