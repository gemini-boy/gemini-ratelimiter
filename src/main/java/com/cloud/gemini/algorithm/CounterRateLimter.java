package com.cloud.gemini.algorithm;

import com.cloud.gemini.common.ResolverEnum;

/**
 * created by fufan on 2019-07-12 18:21
 **/
public interface CounterRateLimter {

    /**
     * 计数法
     * @param key
     * @param limitNum
     * @param second
     */
    public void countLimit(String key, int limitNum, int second, ResolverEnum resolverEnum);
}
