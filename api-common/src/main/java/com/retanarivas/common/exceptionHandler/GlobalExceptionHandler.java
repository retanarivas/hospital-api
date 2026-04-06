package com.retanarivas.common.exceptionHandler;

import com.retanarivas.common.exceptions.BadRequestException;
import com.retanarivas.common.exceptions.ResourceNotFoundException;
import com.retanarivas.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
