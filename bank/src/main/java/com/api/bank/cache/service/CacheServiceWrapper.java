package com.api.bank.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CacheServiceWrapper {
    @Autowired
    private RedisCacheService redisCacheService;

    @Value("${custom.cache.redis-enabled}")
    private Boolean redisEnable;

    public Mono<String> save(String key, String value) {
        if (redisEnable) {
            return redisCacheService.save(key, value);
        }
        return Mono.empty();
    }

    public Mono<String> get(String key) {
        if (redisEnable) {
            return redisCacheService.get(key);
        }
        return Mono.empty();
    }

    public Mono<Boolean> exists(String key) {
        if (redisEnable) {
            return redisCacheService.exitsForKey(key);
        }
        return Mono.just(false);
    }

}
