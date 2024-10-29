package com.tennis.http.seoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SeoulTennisDTO {
    @JsonProperty("list_total_count")
    private int listTotalCount;

    @JsonProperty("RESULT")
    private ResultDTO resultDto;

    @JsonProperty("row")
    private List<RowDTO> rowList;
}
