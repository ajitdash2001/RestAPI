package com.app.ems.dto;

import lombok.Getter;

public class EMSResponseDto {
    private final String message;

    public EMSResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
