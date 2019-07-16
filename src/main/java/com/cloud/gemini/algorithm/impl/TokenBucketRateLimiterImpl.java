package com.cloud.gemini.algorithm.impl;

import com.cloud.gemini.algorithm.TokenBucketRateLimiter;
import com.cloud.gemini.resolver.impl.RedisResolver;
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
public class TokenBucketRateLimiterImpl implements TokenBucketRateLimiter {

    @NonNull
    private final RedisResolver redisResolver;

    public void bucketTokenLimit(String key, int limit, int tokenBucketStepNum, int tokenBucketTimeInterval){
        redisResolver.tokenConsume(key, limit, tokenBucketStepNum, tokenBucketTimeInterval);
    }
}
