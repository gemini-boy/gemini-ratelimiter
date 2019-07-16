package com.cloud.gemini.algorithm;

/**
 * created by fufan on 2019-07-12 18:21
 **/
public interface TokenBucketRateLimiter {

    /**
     * 令牌桶法
     * @param key
     * @param bucketTokenNum 桶
     * @param tokenBucketStepNum
     * @param tokenBucketTimeInterval
     */
    public void bucketTokenLimit(String key, int bucketTokenNum, int tokenBucketStepNum, int tokenBucketTimeInterval);
}
