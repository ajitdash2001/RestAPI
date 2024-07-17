package com.app.ems.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        // Resource not found with id : 100
        super(String.format("%s not found with %s:'%s'",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
