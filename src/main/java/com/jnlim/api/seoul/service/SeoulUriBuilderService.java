package com.jnlim.api.seoul.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class SeoulUriBuilderService {
    @Value("${open.api.seoul.url}")
    private String openApiUrl;

    @Value("${open.api.seoul.key}")
    private String openApiKey;

    @Value("${open.api.seoul.type}")
    private String openApiType;

    @Value("${open.api.seoul.service}")
    private String openApiService;

    public URI buildUriBySeoulTennis(String startIndex, String endIndex, String minClassNm) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(openApiUrl)
                .pathSegment(openApiKey, openApiType, openApiService, startIndex, endIndex, minClassNm);
        URI uri = uriComponentsBuilder.build().encode().toUri();

        log.info("[SeoulUriBuilderService buildUriBySeoulTennis] uri: {}", uri);

        return uri;
    }
}
