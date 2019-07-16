package com.cloud.gemini.config;

import com.cloud.gemini.resolver.RateLimitResolver;
import com.cloud.gemini.resolver.impl.GuavaResolver;
import com.cloud.gemini.resolver.impl.RedisResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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


    @Bean(name = "rateLimitResolver")
    public RateLimitResolver redisResolver() {
        DefaultRedisScript<Long> countScript =new DefaultRedisScript();
        DefaultRedisScript<Long> tokenScript =new DefaultRedisScript();
        countScript.setResultType(Long.class);
        countScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/redis-ratelimiter-counter.lua")));
        tokenScript.setResultType(Long.class);
        tokenScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/redis-ratelimiter-tokenBucket.lua")));
        return new RedisResolver(countScript, tokenScript);
    }


}
