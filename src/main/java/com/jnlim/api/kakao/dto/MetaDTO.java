package com.jnlim.api.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaDTO {
    @JsonProperty("is_end")
    private Boolean isEnd;

    @JsonProperty("pageable_count")
    private Integer pageableCount;

    @JsonProperty("total_count")
    private Integer totalCount;
}
