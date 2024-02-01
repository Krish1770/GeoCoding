package com.example.geoCoding.exceptionHandling;

public class BadRequestException extends RuntimeException{
    public  BadRequestException(String message)
    {
        super(message);
    }
}
