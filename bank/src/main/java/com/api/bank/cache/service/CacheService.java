package com.api.bank.cache.service;

import com.api.bank.cache.Interface.CacheRepository;
import com.api.bank.configuration.LogConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CacheService {

    private final CacheRepository<String, String> cacheRepository;

    static {
        LogConfig.setLogFile(CacheService.class.getName());
    }

    private final Logger logger = LogManager.getLogger(CacheRepository.class);

    public CacheService(CacheRepository<String, String> cacheRepository) {
        this.cacheRepository = cacheRepository;
    }

    public Mono<Void> cacheValue(String key, String value) {
        return Mono.fromRunnable(() -> {
            try {
                cacheRepository.save(key, value).block();
                logger.info("Valor armazenado em cache com chave: {}", key);
            } catch (Exception e) {
                logger.error("Erro ao armazenar valor em cache com chave: {}", key, e);
                throw e;
            }
        });
    }

    public Mono<String> getCachedValue(String key) {
        return Mono.fromCallable(() -> {
            try {
                String value = cacheRepository.get(key).block();
                if (value != null) {
                    logger.info("Valor recuperado com chave: {}", key);
                } else {
                    logger.info("Valor não encontrado para chave: {}", key);
                }
                return value;
            } catch (Exception e) {
                logger.error("Erro ao recuperar valor com chave: {}", key, e);
                throw e;
            }
        });
    }

    public Mono<Boolean> doesKeyExist(String key) {
        return Mono.fromCallable(() -> {
            try {
                boolean exists = cacheRepository.existsForKey(key).block();
                logger.info("A chave {} existe no cache: {}", key, exists);
                return exists;
            } catch (Exception e) {
                logger.error("Erro ao verificar a existência da chave: {}", key, e);
                throw e;
            }
        });
    }
}
