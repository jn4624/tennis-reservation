package com.jnlim.api.kakao.service;

import com.jnlim.api.kakao.dto.DocumentDTO;
import com.jnlim.api.kakao.dto.KakaoApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.Objects;

@Slf4j
@Service
public class KakaoKeywordSearchService {
    private final RestClient restClient;
    private final KakaoUriBuilderService kakaoUriBuilderService;

    public KakaoKeywordSearchService(RestClient.Builder restClientBuilder,
                                     KakaoUriBuilderService kakaoUriBuilderService,
                                     @Value("${open.api.kakao.key}") String openApiKey) {
        this.restClient = restClientBuilder
                .defaultHeader("Authorization", "KakaoAK " + openApiKey)
                .build();
        this.kakaoUriBuilderService = kakaoUriBuilderService;
    }

    public DocumentDTO requestKakaoKeywordSearch(String placeName, double longitude, double latitude) {
        URI uri = kakaoUriBuilderService.buildUriByKakao(placeName, longitude, latitude);

        KakaoApiResponseDTO kakaoApiResponseDto = restClient.get()
                .uri(uri)
                .retrieve()
                .body(KakaoApiResponseDTO.class);

        return getFirstDocument(Objects.requireNonNull(kakaoApiResponseDto));
    }

    private DocumentDTO getFirstDocument(KakaoApiResponseDTO kakaoApiResponseDto) {
        return kakaoApiResponseDto.getFirstDocumentDto()
                .orElseThrow(() -> new RuntimeException("문서 정보가 존재하지 않습니다."));
    }
}
