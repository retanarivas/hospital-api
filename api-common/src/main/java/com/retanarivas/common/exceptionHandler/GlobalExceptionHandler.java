package com.retanarivas.common.exceptionHandler;

import com.retanarivas.common.exceptions.BadRequestException;
import com.retanarivas.common.exceptions.ResourceNotFoundException;
import com.retanarivas.common.exceptions.ServiceUnavailableException;
import com.retanarivas.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGlobalExceptions(Exception ex) {
        return new ResponseEntity<>(ApiResponse.error(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected condition occurred", ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Resource Not Found", ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(
                ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Validation Failed", errorMessage),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        String errorDetail = String.format("The path '%s' with method '%s' does not exist on this server.",
                ex.getRequestURL(), ex.getHttpMethod());

        return new ResponseEntity<>(
                ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Resource Not Found", errorDetail),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ApiResponse<Void>> handleExternalServiceUnavailable(ServiceUnavailableException ex) {
        return new ResponseEntity<>(
                ApiResponse.error(HttpStatus.SERVICE_UNAVAILABLE.value(), "Service Unavailable", ex.getMessage()),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

}
