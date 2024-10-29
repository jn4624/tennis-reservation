package com.tennis.http.seoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultDTO {
    @JsonProperty("CODE")
    private String code;

    @JsonProperty("MESSAGE")
    private String message;
}
