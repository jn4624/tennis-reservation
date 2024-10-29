package com.tennis.http.seoul;

import com.tennis.http.seoul.dto.ResponseDTO;
import com.tennis.http.seoul.dto.RowDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class SeoulHttpClient {
    private final RestTemplate restTemplate;
    private final SeoulHttpUriBuilder seoulHttpUriBuilder;

    public SeoulHttpClient(RestTemplate restTemplate, SeoulHttpUriBuilder seoulHttpUriBuilder) {
        this.restTemplate = restTemplate;
        this.seoulHttpUriBuilder = seoulHttpUriBuilder;
    }

    public List<RowDTO> requestSeoulTennisSearch(String startIndex, String minClassNm) {
        int endIndex = requestSeoulTennisTotalCount(startIndex, startIndex, minClassNm);

        log.info("[SeoulHttpClient requestSeoulTennisSearch] listTotalCount: {}", endIndex);

        URI uri = seoulHttpUriBuilder.buildUriBySeoulTennis(startIndex, String.valueOf(endIndex), minClassNm);

        log.info("[SeoulHttpClient requestSeoulTennisSearch] uri: {}", uri);

        return Objects.requireNonNull(restTemplate.getForObject(uri, ResponseDTO.class))
                .getSeoulTennisDto()
                .getRowList();
    }

    private int requestSeoulTennisTotalCount(String startIndex, String endIndex, String minClassNm) {
        URI uri = seoulHttpUriBuilder.buildUriBySeoulTennis(startIndex, endIndex, minClassNm);

        log.info("[SeoulHttpClient requestSeoulTennisTotalCount] uri: {}", uri);

        return Objects.requireNonNull(restTemplate.getForObject(uri, ResponseDTO.class))
                .getSeoulTennisDto()
                .getListTotalCount();
    }
}
