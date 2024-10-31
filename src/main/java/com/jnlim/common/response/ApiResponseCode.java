package com.jnlim.common.response;

import lombok.Getter;

@Getter
public enum ApiResponseCode {
    SUCCESS(200, "Success");

    private final int code;
    private final String message;

    ApiResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
