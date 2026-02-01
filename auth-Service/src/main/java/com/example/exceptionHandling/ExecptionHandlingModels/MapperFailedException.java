package com.example.exceptionHandling.ExecptionHandlingModels;


public class MapperFailedException extends RuntimeException {
    public MapperFailedException(String message) {
        super(message);
    }
}
