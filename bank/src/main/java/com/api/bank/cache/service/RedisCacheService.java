package com.api.bank.cache.service;

import com.api.bank.cache.repository.CacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RedisCacheService {

    @Autowired
    private CacheRepository cacheRepository;

    private final Logger logger = LogManager.getLogger(RedisCacheService.class);

    public Mono<String> save(String key, String value) {
        try {
            return cacheRepository
                    .save(key, value)
                    .flatMap(saved -> {
                        if (saved) {
                            logger.info("Cache salvo para chave {}", key);
                        } else {
                            logger.info("Não foi possível salvar cache para chave {}", key);
                        }
                        return Mono.just(value);
                    });
        } catch (Exception ex) {
            logger.error("Erro ao tentar salvar cache para a chave {}", key, ex);
        }
        return Mono.just(value);
    }

    public Mono<String> get(String key) {
        try {
            return cacheRepository
                    .get(key)
                    .doOnNext(response -> {
                        logger.info("Retornando cache para a chave {}", key);
                    });
        } catch (Exception ex) {
            logger.error("Erro ao tentar recuperar cache para a chave {}", key, ex);
        }
        return Mono.empty();
    }

    public Mono<Boolean> exitsForKey(String key) {
        try {
            return cacheRepository
                    .exitsForKey(key)
                    .doOnNext(exits -> {
                        if (exits) {
                            logger.info("Cache existente para a chave {}", key);
                        } else {
                            logger.info("Cache não existente para a chave {}", key);
                        }
                    });
        } catch (Exception ex) {
            logger.error("Erro ao tentar recuperar cache para a chave {}", key, ex);
        }
        return Mono.just(false);
    }

}
