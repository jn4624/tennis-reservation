package com.jnlim.tennis.service.impl;

import com.jnlim.api.kakao.service.KakaoKeywordSearchService;
import com.jnlim.cache.RedisTemplateService;
import com.jnlim.tennis.dto.TennisDTO;
import com.jnlim.tennis.repository.TennisMapper;
import com.jnlim.tennis.service.TennisService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TennisServiceImpl implements TennisService {
    private static final String REDIRECT_URL = "https://map.kakao.com/link/map/";

    private final TennisMapper tennisMapper;
    private final RedisTemplateService redisTemplateService;
    private final KakaoKeywordSearchService kakaoKeywordSearchService;

    public TennisServiceImpl(TennisMapper tennisMapper, RedisTemplateService redisTemplateService, KakaoKeywordSearchService kakaoKeywordSearchService) {
        this.tennisMapper = tennisMapper;
        this.redisTemplateService = redisTemplateService;
        this.kakaoKeywordSearchService = kakaoKeywordSearchService;
    }

    @Cacheable(cacheNames = "getTennisList",
                key = "'tennis:page' + #page + ':size:' + #size",
                cacheManager = "tennisCacheManager")
    @Override
    public List<TennisDTO> getTennisList(int page, int size) {
        return tennisMapper.findAllByPageable(page, size);
    }

    @Override
    public TennisDTO getTennis(Long id) {
        String key = "getTennis::tennis:id:" + id;
        Optional<TennisDTO> cache = redisTemplateService.get(key, TennisDTO.class);

        if (cache.isPresent()) {
            return cache.get();
        }

        TennisDTO databaseData = tennisMapper.findById(id);
        redisTemplateService.set(key, databaseData, 10L);

        return databaseData;
    }

    @Override
    public String getTennisLocationURL(Long id) {
        String placeId = getPlaceIdFromKakao(tennisMapper.findById(id));
        return REDIRECT_URL + placeId;
    }

    private String getPlaceIdFromKakao(TennisDTO tennisDto) {
        return kakaoKeywordSearchService
                .requestKakaoKeywordSearch(tennisDto.getPlaceName(), tennisDto.getLongitude(), tennisDto.getLatitude())
                .getPlaceId();
    }
}
