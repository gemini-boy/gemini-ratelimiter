package com.cloud.gemini.resolver.impl;

import com.cloud.gemini.common.RateLimitConstant;
import com.cloud.gemini.exception.RateLimitException;
import com.cloud.gemini.resolver.RateLimitResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.ArrayList;
import java.util.List;

/**
 * created by fufan on 2019-07-12 11:20
 **/

public class RedisResolver extends RateLimitResolver {

    @Autowired
    RedisTemplate redisTemplate;

    DefaultRedisScript<Long> countScript;

    DefaultRedisScript<Long> tokenScript;

    public RedisResolver(DefaultRedisScript<Long> countScript,
                         DefaultRedisScript<Long> tokenScript) {
        this.countScript = countScript;
        this.tokenScript = tokenScript;
    }

    @Override
    public void countConsume(String key, int count, int slideTimeWindow) {
        List<Object> keyList = new ArrayList();
        keyList.add(String.valueOf(key));
        keyList.add(String.valueOf(count));
        keyList.add(String.valueOf(slideTimeWindow));
        String result=redisTemplate.execute(countScript,keyList,keyList).toString();
        if(RateLimitConstant.REDIS_ERROR.equals(result)){
            throw new RateLimitException(RateLimitException.RateLimitErrorEnum.TOO_MANY_REQUEST.getDescription());
        }
    }

    @Override
    public void tokenConsume(String key, int limit, int tokenBucketStepNum, int tokenBucketTimeInterval) {
        List<Object> keyList = new ArrayList();
        keyList.add(key);
        keyList.add(String.valueOf(limit));
        keyList.add(String.valueOf(tokenBucketStepNum));
        keyList.add(String.valueOf(tokenBucketTimeInterval));
        keyList.add(String.valueOf(System.currentTimeMillis()/1000));
        String result=redisTemplate.execute(tokenScript,keyList,keyList).toString();
        if(RateLimitConstant.REDIS_ERROR.equals(result)){
            throw new RateLimitException(RateLimitException.RateLimitErrorEnum.TOO_MANY_REQUEST.getDescription());
        }

    }
}
