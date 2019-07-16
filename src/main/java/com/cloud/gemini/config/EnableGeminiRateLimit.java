package com.cloud.gemini.config;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(GenminiRateLimitConfiguration.class)
@Documented
@Inherited
public @interface EnableGeminiRateLimit {

}
