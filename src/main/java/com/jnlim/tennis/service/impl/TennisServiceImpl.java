package com.jnlim.tennis.service.impl;

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
    private final TennisMapper tennisMapper;
    private final RedisTemplateService redisTemplateService;

    public TennisServiceImpl(TennisMapper tennisMapper, RedisTemplateService redisTemplateService) {
        this.tennisMapper = tennisMapper;
        this.redisTemplateService = redisTemplateService;
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
}
