package com.jnlim.api.kakao.service;

import com.jnlim.api.kakao.dto.DocumentDTO;
import com.jnlim.api.kakao.dto.KakaoApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Service
public class KakaoKeywordSearchService {
    private final WebClient webClient;

    public KakaoKeywordSearchService(WebClient.Builder webClientBuilder,
                                     @Value("${open.api.kakao.url}") String baseUrl,
                                     @Value("${open.api.kakao.key}") String openApiKey) {
        this.webClient = webClientBuilder
                .baseUrl(baseUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("Authorization", "KakaoAK " + openApiKey);
                })
                .build();
    }

    public DocumentDTO requestKakaoKeywordSearch(String placeName, double longitude, double latitude) {
        KakaoApiResponseDTO kakaoApiResponseDto = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/local/search/keyword.JSON")
                        .queryParam("query", placeName)
                        .queryParam("x", longitude)
                        .queryParam("y", latitude)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException("client error: " + clientResponse.statusCode())))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException("server error: " + clientResponse.statusCode())))
                .bodyToMono(KakaoApiResponseDTO.class)
                .block();

        return getFirstDocument(Objects.requireNonNull(kakaoApiResponseDto));
    }

    private DocumentDTO getFirstDocument(KakaoApiResponseDTO kakaoApiResponseDto) {
        return kakaoApiResponseDto.getFirstDocumentDto()
                .orElseThrow(() -> new RuntimeException("문서 정보가 존재하지 않습니다."));
    }
}