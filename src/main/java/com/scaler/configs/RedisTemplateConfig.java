package com.scaler.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;



@Configuration
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String, Object> getRedisTemplste(RedisConnectionFactory redisConnectionFactory ) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
