package com.jnlim.api.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DocumentDTO {
    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("id")
    private String placeId;

    @JsonProperty("place_name")
    private String placeName;

    @JsonProperty("place_url")
    private String placeUrl;
}
