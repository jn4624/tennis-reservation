package com.jnlim.api.seoul.service;

import com.jnlim.api.seoul.dto.SeoulApiResponseDTO;
import com.jnlim.api.seoul.dto.RowDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class SeoulTennisSearchService {
    private final RestTemplate restTemplate;
    private final SeoulUriBuilderService seoulUriBuilderService;

    public SeoulTennisSearchService(RestTemplate restTemplate, SeoulUriBuilderService seoulUriBuilderService) {
        this.restTemplate = restTemplate;
        this.seoulUriBuilderService = seoulUriBuilderService;
    }

    public List<RowDTO> requestSeoulTennisSearch(String startIndex, String minClassNm) {
        int endIndex = requestSeoulTennisTotalCount(startIndex, startIndex, minClassNm);

        log.info("[SeoulTennisSearchService requestSeoulTennisSearch] listTotalCount: {}", endIndex);

        URI uri = seoulUriBuilderService.buildUriBySeoulTennis(startIndex, String.valueOf(endIndex), minClassNm);

        log.info("[SeoulTennisSearchService requestSeoulTennisSearch] uri: {}", uri);

        return Objects.requireNonNull(restTemplate.getForObject(uri, SeoulApiResponseDTO.class))
                .getListPublicReservationSportDto()
                .getRowList();
    }

    private int requestSeoulTennisTotalCount(String startIndex, String endIndex, String minClassNm) {
        URI uri = seoulUriBuilderService.buildUriBySeoulTennis(startIndex, endIndex, minClassNm);

        log.info("[SeoulTennisSearchService requestSeoulTennisTotalCount] uri: {}", uri);

        return Objects.requireNonNull(restTemplate.getForObject(uri, SeoulApiResponseDTO.class))
                .getListPublicReservationSportDto()
                .getListTotalCount();
    }
}
