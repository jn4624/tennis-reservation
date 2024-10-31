package com.jnlim.common.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public ApiResponse(ApiResponseCode apiResponseCode, T data) {
        this.code = apiResponseCode.getCode();
        this.message = apiResponseCode.getMessage();
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ApiResponseCode.SUCCESS, data);
    }
}
