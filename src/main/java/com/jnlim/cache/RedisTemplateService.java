package com.jnlim.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Service
public class RedisTemplateService {
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisTemplateService(RedisTemplate<String, String> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> Optional<T> get(String key, Class<T> classType) {
        String value = redisTemplate.opsForValue().get(key);

        if (value == null) {
            log.error("[RedisTemplateService get] cache miss for key: {}", key);
            return Optional.empty();
        }

        try {
            log.info("[RedisTemplateService get] cache hit for key: {}", key);
            return Optional.of(objectMapper.readValue(value, classType));
        } catch (JsonProcessingException e) {
            log.error("[RedisTemplateService get] failed to deserialize value for key: {}, {}", key, e.getMessage());
            throw new IllegalStateException("failed to deserialize value for key: " + key, e);
        }
    }

    public <T> void set(String key, T data, long minutes) {
        try {
            String value = objectMapper.writeValueAsString(data);
            redisTemplate.opsForValue().set(key, value, Duration.ofMinutes(minutes));
        } catch (JsonProcessingException e) {
            log.info("[RedisTemplateService set] failed to serialize data for key: {}, {}", key, e.getMessage());
            throw new IllegalStateException("failed to serialize data for key: " + key, e);
        }
    }
}
