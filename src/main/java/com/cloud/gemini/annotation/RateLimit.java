package com.cloud.gemini.annotation;

import com.cloud.gemini.common.AlgorithmEnum;
import com.cloud.gemini.common.ResolverEnum;

import java.lang.annotation.*;

/**
 * created by fufan on 2019-07-12 11:42
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RateLimit {

    public int limitTime() default 10;

    public int interval() default 60;

    public int tokenBucketTimeInterval() default 10;

    public int tokenBucketStepNum() default 5;

    public AlgorithmEnum algorithm() default AlgorithmEnum.Counter;

    public String limitKey() default "all";

//    public String limitFunction();
//
//
//    @FunctionalInterface
//    public interface LimitFunction {
//
//        public String isLimit();
//    }
}
