package com.api.bank.cache.Interface;

import reactor.core.publisher.Mono;

public interface CacheRepository<K, V> {

    Mono<Void> save(K key, V value);

    Mono<V> get(K key);

    Mono<Boolean> existsForKey(K key);
}
