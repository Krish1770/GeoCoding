package com.example.geoCoding.exceptionHandling;

import com.example.geoCoding.dTO.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ResponseDto> handleException(MethodArgumentNotValidException message, HttpServletRequest request) {

        HashMap<String, String> errorMap = new LinkedHashMap<>();
        for (ObjectError error : message.getBindingResult().getAllErrors()) {
            errorMap.put(((FieldError) error).getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("error occured", errorMap, HttpStatus.BAD_REQUEST));
    }
}
