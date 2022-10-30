package com.lviv.iot.controller;

import com.lviv.iot.exception.CityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cityNotFoundHandler(CityNotFoundException ex) {
        return ex.getMessage();
    }
}
