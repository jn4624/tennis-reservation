package com.jnlim.api.kakao.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class KakaoUriBuilderService {
    @Value("${open.api.kakao.url}")
    private String baseUrl;

    public URI buildUriByKakao(String placeName, double longitude, double latitude) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/v2/local/search/keyword.JSON")
                .queryParam("query", placeName)
                .queryParam("x", longitude)
                .queryParam("y", latitude)
                .build()
                .encode()
                .toUri();

        log.info("[KakaoUriBuilderService buildUriByKakao] uri: {}", uri);

        return uri;
    }
}
