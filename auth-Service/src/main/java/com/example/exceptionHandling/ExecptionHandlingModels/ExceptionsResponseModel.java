package com.example.exceptionHandling.ExecptionHandlingModels;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Serdeable
@Getter
@Setter
public class ExceptionsResponseModel {

    private String errorType;
    private String errorMessage;
    private String errorLocation;
    private LocalDateTime errorTime;
}
