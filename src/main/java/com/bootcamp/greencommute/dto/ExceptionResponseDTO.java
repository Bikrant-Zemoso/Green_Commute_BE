package com.bootcamp.greencommute.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponseDTO {

    private Date timestamp;
    private String error;
    private String message;

    public ExceptionResponseDTO(String error, String message) {
        this.error = error;
        this.message = message;
        this.timestamp = new Date();
    }
}
