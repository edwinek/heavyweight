package com.example.ehdee.heavyweight.web;


import com.example.ehdee.heavyweight.exception.HeavyweightServiceDateException;
import com.example.ehdee.heavyweight.model.HeavyweightResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(HeavyweightServiceDateException.class)
    public HeavyweightResponse handleDateError(HeavyweightServiceDateException exception) {
        return new HeavyweightResponse.Builder().withError(exception.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler({MissingServletRequestParameterException.class, NoHandlerFoundException.class})
    public ResponseEntity<String> handleConflict() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad juju.");
    }

}
