package com.cloud.gemini.config;

import com.cloud.gemini.resolver.GuavaLimitResolver;
import com.cloud.gemini.resolver.RedisLimitResolver;
import com.cloud.gemini.resolver.impl.GuavaLimitResolverImpl;
import com.cloud.gemini.resolver.impl.RedisLimitResolverImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 *
 * created by fufan on 2019-07-12 13:28
 **/
@Slf4j
@Configuration
@ComponentScan("com.cloud.gemini")
public class GenminiRateLimitConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean(name= "guavaLimitResolver")
//    @ConditionalOnProperty(prefix = "gemini", value = "resolver", havingValue = "guava")
    public GuavaLimitResolver guavaResolver() {

        return new GuavaLimitResolverImpl();
    }


    @Bean(name = "redisLimitResolver")
//    @ConditionalOnProperty(prefix = "gemini", value = "resolver", havingValue = "redis")
    public RedisLimitResolver redisResolver() {
        DefaultRedisScript<Long> countScript =new DefaultRedisScript();
        DefaultRedisScript<Long> tokenScript =new DefaultRedisScript();
        countScript.setResultType(Long.class);
        countScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/redis-ratelimiter-counter.lua")));
        tokenScript.setResultType(Long.class);
        tokenScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/redis-ratelimiter-tokenBucket.lua")));
        return new RedisLimitResolverImpl(countScript, tokenScript);
    }


}
