package com.cloud.gemini.resolver;

/**
 * created by fufan on 2019-07-19 16:31
 **/
public interface GuavaLimitResolver {

    /**
     * 令牌桶限流
     * @param key
     * @param limit
     * @param tokenBucketStepNum
     * @param tokenBucketTimeInterval
     */
    public void tokenConsume(String key, int limit, int tokenBucketStepNum, int tokenBucketTimeInterval);
}
