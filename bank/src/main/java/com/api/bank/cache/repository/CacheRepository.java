package com.api.bank.cache.repository;

import com.api.bank.configuration.LogConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Repository
public class CacheRepository {

    @Autowired
    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;
    private final Logger logger = LogManager.getLogger(CacheRepository.class);

    public CacheRepository() {
        LogConfig.setLogFile("CacheRepository");
    }

    @Value("${custom.cache.ttl}")
    private Integer ttl;

    public Mono<Boolean> save(String key, String value) {
        return reactiveRedisTemplate
                .opsForValue()
                .set(key, value)
                .then(reactiveRedisTemplate.expire(key, Duration.ofSeconds(ttl)))
                .onErrorResume(throwable -> {
                    logger.error("Erro ao tentar salvar os dados no Redis para a chave: {}", key, throwable);
                    return Mono.just(false);
                });
    }

    public Mono<String> get(String key) {
        return reactiveRedisTemplate
                .opsForValue()
                .get(key)
                .onErrorResume(throwable -> {
                    logger.error("Erro ao tentar recuperar dados no Redis para a chave: {}", key, throwable);
                    return Mono.empty();
                });
    }

    public Mono<Boolean> exitsForKey(String key) {
        return reactiveRedisTemplate
                .hasKey(key)
                .onErrorResume(throwable -> {
                    logger.error("Erro ao tentar consultar dados no Redis para a chave: {}", key, throwable);
                    return Mono.just(false);
                });
    }

}
