package com.tennis.http.seoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ResponseDTO {
    @JsonProperty("ListPublicReservationSport")
    private SeoulTennisDTO seoulTennisDto;
}
