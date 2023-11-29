package com.api.bank.cache.service;

import com.api.bank.cache.Interface.CacheRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CacheServiceTest {

    @Mock
    private CacheRepository<String, String> cacheRepository;

    @InjectMocks
    private CacheService cacheService;

    @Test
    public void testCacheValue() {
        String key = "testKey";
        String value = "testValue";

        when(cacheRepository.save(key, value)).thenReturn(Mono.empty());

        StepVerifier.create(cacheService.cacheValue(key, value))
                .verifyComplete();

        verify(cacheRepository, times(1)).save(key, value);
    }

    @Test
    public void testGetCachedValue() {
        String key = "testKey";
        String expectedValue = "cachedValue";

        when(cacheRepository.get(key)).thenReturn(Mono.just(expectedValue));

        StepVerifier.create(cacheService.getCachedValue(key))
                .expectNext(expectedValue)
                .verifyComplete();

        verify(cacheRepository, times(1)).get(key);
    }

    @Test
    public void testDoesKeyExist() {
        String key = "testKey";

        when(cacheRepository.existsForKey(key)).thenReturn(Mono.just(true));

        StepVerifier.create(cacheService.doesKeyExist(key))
                .expectNext(true)
                .verifyComplete();

        verify(cacheRepository, times(1)).existsForKey(key);
    }
}
