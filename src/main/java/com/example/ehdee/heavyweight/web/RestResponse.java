package com.example.ehdee.heavyweight.web;

import org.springframework.http.HttpStatus;

public class RestResponse {

    private HttpStatus status;
    private String message;

    public RestResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
