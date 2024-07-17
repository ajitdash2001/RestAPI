package com.app.ems.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class EmployeeAPIException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public EmployeeAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
