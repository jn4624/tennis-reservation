package com.jnlim.api.kakao.service;

import com.jnlim.api.kakao.dto.DocumentDTO;
import com.jnlim.api.kakao.dto.KakaoApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Slf4j
@Service
public class KakaoKeywordSearchService {
    private final RestClient restClient;

    public KakaoKeywordSearchService(RestClient.Builder restClientBuilder,
                                     @Value("${open.api.kakao.url}") String baseUrl,
                                     @Value("${open.api.kakao.key}") String openApiKey) {
        this.restClient = restClientBuilder
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", "KakaoAK " + openApiKey)
                .build();
    }

    public DocumentDTO requestKakaoKeywordSearch(String placeName, double longitude, double latitude) {
        KakaoApiResponseDTO kakaoApiResponseDto = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/local/search/keyword.JSON")
                        .queryParam("query", placeName)
                        .queryParam("x", longitude)
                        .queryParam("y", latitude)
                        .build())
                .retrieve()
                .body(KakaoApiResponseDTO.class);

        return getFirstDocument(Objects.requireNonNull(kakaoApiResponseDto));
    }

    private DocumentDTO getFirstDocument(KakaoApiResponseDTO kakaoApiResponseDto) {
        return kakaoApiResponseDto.getFirstDocumentDto()
                .orElseThrow(() -> new RuntimeException("문서 정보가 존재하지 않습니다."));
    }
}
