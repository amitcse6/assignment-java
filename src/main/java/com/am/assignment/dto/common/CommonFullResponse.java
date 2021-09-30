package com.am.assignment.dto.common;

import lombok.Data;

@Data
public class CommonFullResponse {
    private int statusCode;
    private String message;
    private String error;

    public CommonFullResponse(int statusCode, String message, String error) {
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
    }
}
