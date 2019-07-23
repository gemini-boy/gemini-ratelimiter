package com.cloud.gemini.algorithm.impl;

import com.cloud.gemini.algorithm.TokenBucketRateLimiter;
import com.cloud.gemini.common.ResolverEnum;
import com.cloud.gemini.resolver.GuavaLimitResolver;
import com.cloud.gemini.resolver.RedisLimitResolver;
import com.sun.istack.internal.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * created by fufan on 2019-07-12 16:01
 **/
@Service
@DependsOn({"redisLimitResolver", "guavaLimitResolver"})
@RequiredArgsConstructor
public class TokenBucketRateLimiterImpl implements TokenBucketRateLimiter {

    @NonNull
    private final RedisLimitResolver redisLimitResolver;

    @NotNull
    private final GuavaLimitResolver guavaLimitResolver;

    public void bucketTokenLimit(String key, int limit, int tokenBucketStepNum, int tokenBucketTimeInterval, ResolverEnum resolverEnum){
        switch (resolverEnum) {
            case REDIS:
                redisLimitResolver.tokenConsume(key, limit, tokenBucketStepNum, tokenBucketTimeInterval);
            case GUAVA:
                guavaLimitResolver.tokenConsume(key, limit, tokenBucketStepNum, tokenBucketTimeInterval);
            default:
        }
    }
}
