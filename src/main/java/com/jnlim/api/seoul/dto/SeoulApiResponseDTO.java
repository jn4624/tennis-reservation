package com.jnlim.api.seoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SeoulApiResponseDTO {
    @JsonProperty("ListPublicReservationSport")
    private ListPublicReservationSportDTO listPublicReservationSportDto;
}
