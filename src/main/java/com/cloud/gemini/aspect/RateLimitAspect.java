package com.cloud.gemini.aspect;

import com.cloud.gemini.algorithm.CounterRateLimter;
import com.cloud.gemini.algorithm.TokenBucketRateLimiter;
import com.cloud.gemini.annotation.RateLimit;
import com.cloud.gemini.util.RateLimitUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * created by fufan on 2019-07-12 15:45
 **/
@Component
@Aspect
@Slf4j
public class RateLimitAspect {


    @Autowired
    CounterRateLimter counterRateLimter;

    @Autowired
    TokenBucketRateLimiter tokenBucketRateLimiter;


    /**
     * 方法拦截注解切点
     * @param rateLimit 注解
     */
    @Pointcut("@annotation(rateLimit)")
    public void annotationPointcut(RateLimit rateLimit) {

    }

    /**
     * 被@rateLimit注解标识的方法被调用
     * @param joinPoint 切点
     * @param rateLimit 注解
     */
    @Before("annotationPointcut(rateLimit)")
    public void doBefore(JoinPoint joinPoint, RateLimit rateLimit) {

        String key = RateLimitUtil.generateKey(joinPoint, rateLimit.limitKey());
        switch (rateLimit.algorithm() ) {
            case Counter:
                counterRateLimter.countLimit(key, rateLimit.limitTime(), rateLimit.interval());
                break;
            case TokenBucket:
                tokenBucketRateLimiter.bucketTokenLimit(key, rateLimit.limitTime(), rateLimit.tokenBucketStepNum(), rateLimit.tokenBucketTimeInterval());
                break;
            default:
        }

    }



}
