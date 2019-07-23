package com.cloud.gemini.algorithm.impl;

import com.cloud.gemini.algorithm.CounterRateLimter;
import com.cloud.gemini.common.ResolverEnum;
import com.cloud.gemini.resolver.GuavaLimitResolver;
import com.cloud.gemini.resolver.RedisLimitResolver;
import com.sun.istack.internal.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * created by fufan on 2019-07-12 16:01
 **/
@Service
@DependsOn({"redisLimitResolver", "guavaLimitResolver"})
@RequiredArgsConstructor
@Slf4j
public class CounterRateLimterImpl implements CounterRateLimter {

    @NonNull
    private final RedisLimitResolver redisLimitResolver;

    public void countLimit(String key, int limitNum, int second, ResolverEnum resolverEnum) {
        switch (resolverEnum) {
            case REDIS:
                redisLimitResolver.countConsume(key, limitNum, second);
            case GUAVA:
                log.info("ERROR, Counter algorithm is not supported by GUAVA ");
        }
    }


}
