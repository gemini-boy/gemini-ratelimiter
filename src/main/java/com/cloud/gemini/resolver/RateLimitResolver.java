package com.cloud.gemini.resolver;

/**
 * created by fufan on 2019-07-12 11:33
 **/
public abstract class RateLimitResolver {

    /**
     * 计数法限流
     * @param key 限流队列key
     * @param count 时间窗口里的限流次数
     * @param timeWindow 限流时间窗口
     */
    public void countConsume(String key, int count, int timeWindow) {};


    /**
     * 令牌桶限流
     * @param key
     * @param limit
     * @param tokenBucketStepNum
     * @param tokenBucketTimeInterval
     */
    public void tokenConsume(String key, int limit, int tokenBucketStepNum, int tokenBucketTimeInterval) {};
}
