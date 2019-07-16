package com.cloud.gemini.util;

import com.google.common.base.Joiner;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;


/**
 * created by fufan on 2019-07-13 16:08
 **/
public class RateLimitUtil {

    /**
     * 生成限流的key
     * @param joinPoint
     * @param customKey
     * @return
     */
    public static String generateKey(JoinPoint joinPoint, String customKey) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = methodSignature.getMethod().getName();
        String paramTypes = Joiner.on(",").join(methodSignature.getParameterTypes());
        return Joiner.on("||").join(className, methodName, paramTypes, customKey);

    }
}
