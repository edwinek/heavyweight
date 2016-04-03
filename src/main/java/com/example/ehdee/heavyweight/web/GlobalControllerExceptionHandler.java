package com.example.ehdee.heavyweight.web;


import com.example.ehdee.heavyweight.exception.HeavyweightServiceDateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HeavyweightServiceDateException.class)
    public RestResponse handleDateError(HeavyweightServiceDateException exception) {
        return new RestResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({MissingServletRequestParameterException.class, NoHandlerFoundException.class})
    public RestResponse handleConflict() {
        return new RestResponse(HttpStatus.BAD_REQUEST, "");
    }

}
