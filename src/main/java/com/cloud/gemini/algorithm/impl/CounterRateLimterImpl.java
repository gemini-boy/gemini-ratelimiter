package com.cloud.gemini.algorithm.impl;

import com.cloud.gemini.algorithm.CounterRateLimter;
import com.cloud.gemini.resolver.RateLimitResolver;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * created by fufan on 2019-07-12 16:01
 **/
@Service
@DependsOn("rateLimitResolver")
@RequiredArgsConstructor
public class CounterRateLimterImpl implements CounterRateLimter {

    @NonNull
    private final RateLimitResolver rateLimitResolver;

    public void countLimit(String key, int limitNum, int second) {
        rateLimitResolver.countConsume(key, limitNum, second);
    }


}
