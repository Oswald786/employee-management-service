package com.example.exceptionHandling.ExceptionHandlingHandlers;

import com.example.exceptionHandling.ExecptionHandlingModels.ExceptionsResponseModel;
import com.example.exceptionHandling.ExecptionHandlingModels.MapperFailedException;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

import java.time.LocalDateTime;

@Produces
@Singleton
@Requires(classes = MapperFailedException.class)
public class MapperFailedExceptionHandler implements ExceptionHandler<MapperFailedException, HttpResponse<ExceptionsResponseModel>> {

    @Override
    public HttpResponse<ExceptionsResponseModel> handle(HttpRequest request, MapperFailedException exception) {
        ExceptionsResponseModel exceptionsResponseModel = new ExceptionsResponseModel();
        exceptionsResponseModel.setErrorType(MapperFailedException.class.getSimpleName());
        exceptionsResponseModel.setErrorMessage(exception.getMessage());
        exceptionsResponseModel.setErrorLocation(request.getPath());
        exceptionsResponseModel.setErrorTime(LocalDateTime.now());
        return HttpResponse.serverError(exceptionsResponseModel);
    }
}
