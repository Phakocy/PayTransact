package com.interswitch.paytransact.config;

import com.interswitch.paytransact.entities.commons.ApiError;
import com.interswitch.paytransact.exceptions.MainExceptions;
import com.interswitch.paytransact.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler({MainExceptions.class, NotFoundException.class})
    final ResponseEntity<ApiError> handleExceptions(Exception ex) {
        if (ex instanceof NotFoundException) {
            return new ResponseEntity<>(new ApiError(ex.getMessage()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiError(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}