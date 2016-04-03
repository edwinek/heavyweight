package com.example.ehdee.heavyweight.web;


import com.example.ehdee.heavyweight.exception.HeavyweightServiceDateException;
import com.example.ehdee.heavyweight.model.HeavyweightResponse;
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
    public HeavyweightResponse handleDateError(HeavyweightServiceDateException exception) {
        return new HeavyweightResponse.Builder().withError(exception.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({MissingServletRequestParameterException.class, NoHandlerFoundException.class})
    public HeavyweightResponse handleConflict() {
        return new HeavyweightResponse.Builder().withError("Unspecified error.").build();
    }

}
