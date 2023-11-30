package com.api.bank.cache.repository;

import com.api.bank.cache.Interface.CacheRepository;
import com.api.bank.configuration.LogConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class RedisCacheRepository<K, V> implements CacheRepository<K, V> {

    private final Logger logger = LogManager.getLogger(CacheRepository.class);

    static {
        LogConfig.setLogFile(CacheRepository.class.getName());
    }

    private final ReactiveRedisTemplate<K, V> reactiveRedisTemplate;

    public RedisCacheRepository(ReactiveRedisTemplate<K, V> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    @Override
    public Mono<Void> save(K key, V value) {
        return Mono.fromRunnable(() -> {
            try {
                reactiveRedisTemplate.opsForValue().set(key, value).block();
                logger.debug("Valor salvo com chave: {}", key);
            } catch (Exception e) {
                logger.error("Erro ao salvar valor com chave: {}", key, e);
            }
        });
    }

    @Override
    public Mono<V> get(K key) {
        return Mono.fromCallable(() -> {
            try {
                V value = reactiveRedisTemplate.opsForValue().get(key).block();
                if (value != null) {
                    logger.debug("Valor recuperado com chave: {}", key);
                } else {
                    logger.debug("Valor não encontrado para chave: {}", key);
                }
                return value;
            } catch (Exception e) {
                logger.error("Erro ao recuperar valor com chave: {}", key, e);
                throw e;
            }
        });
    }

    @Override
    public Mono<Boolean> existsForKey(K key) {
        return Mono.fromCallable(() -> {
            try {
                boolean exists = reactiveRedisTemplate.hasKey(key).block();
                logger.debug("A chave {} existe no cache: {}", key, exists);
                return exists;
            } catch (Exception e) {
                logger.error("Erro ao verificar a existência da chave: {}", key, e);
                throw e;
            }
        });
    }
}
