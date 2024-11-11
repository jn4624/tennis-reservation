package com.jnlim.api.seoul.service;

import com.jnlim.api.seoul.dto.ListPublicReservationSportDTO;
import com.jnlim.api.seoul.dto.RowDTO;
import com.jnlim.api.seoul.dto.SeoulApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SeoulTennisSearchService {
    private final RestClient restClient;
    private final SeoulUriBuilderService seoulUriBuilderService;

    public SeoulTennisSearchService(RestClient.Builder restClientBuilder, SeoulUriBuilderService seoulUriBuilderService) {
        this.restClient = restClientBuilder.build();
        this.seoulUriBuilderService = seoulUriBuilderService;
    }

    public List<RowDTO> requestSeoulTennisSearch(String startIndex, String minClassNm) {
        int endIndex = requestSeoulTennisTotalCount(startIndex, startIndex, minClassNm);

        log.info("[SeoulTennisSearchService requestSeoulTennisSearch] listTotalCount: {}", endIndex);

        URI uri = seoulUriBuilderService.buildUriBySeoulTennis(startIndex, String.valueOf(endIndex), minClassNm);

        log.info("[SeoulTennisSearchService requestSeoulTennisSearch] uri: {}", uri);

        SeoulApiResponseDTO seoulApiResponseDto = restClient.get()
                .uri(uri)
                .retrieve()
                .body(SeoulApiResponseDTO.class);

        return Optional.ofNullable(seoulApiResponseDto)
                .map(SeoulApiResponseDTO::getListPublicReservationSportDto)
                .map(ListPublicReservationSportDTO::getRowList)
                .orElseThrow(() -> new RuntimeException("응답에서 데이터를 찾을 수 없습니다."));
    }

    private int requestSeoulTennisTotalCount(String startIndex, String endIndex, String minClassNm) {
        URI uri = seoulUriBuilderService.buildUriBySeoulTennis(startIndex, endIndex, minClassNm);

        log.info("[SeoulTennisSearchService requestSeoulTennisTotalCount] uri: {}", uri);

        SeoulApiResponseDTO seoulApiResponseDto = restClient.get()
                .uri(uri)
                .retrieve()
                .body(SeoulApiResponseDTO.class);

        return Optional.ofNullable(seoulApiResponseDto)
                .map(SeoulApiResponseDTO::getListPublicReservationSportDto)
                .map(ListPublicReservationSportDTO::getListTotalCount)
                .orElseThrow(() -> new RuntimeException("응답에서 데이터를 찾을 수 없습니다."));
    }
}
