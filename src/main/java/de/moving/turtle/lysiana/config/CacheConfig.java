package de.moving.turtle.lysiana.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import static java.util.Collections.singletonList;

@EnableCaching
@Configuration
public class CacheConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);


    @Bean
    public CacheManager cacheManager(final Ticker ticker,
                                    @Value("${schedule.suncalc.cache.ttl}") int suncalcCacheTtl) {
        final CaffeineCache suncalcResultsCache = buildCache("suncalcResultsCache", ticker, suncalcCacheTtl);
        final SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(singletonList(suncalcResultsCache));
        return manager;
    }

    private CaffeineCache buildCache(
            final String name,
            final Ticker ticker,
            final int minutesToExpire) {
        return new CaffeineCache(name, Caffeine.newBuilder()
                .expireAfterWrite(minutesToExpire, TimeUnit.MINUTES)
                .maximumSize(100)
                .ticker(ticker)
                .build());
    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }
}
