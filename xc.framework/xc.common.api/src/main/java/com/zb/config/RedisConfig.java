package com.zb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /*
     <bean id="redisTemplate" class="org.springframework.data.redis.core.StringRedisTemplate">
        <property name="connectionFactory" ref="connectionFactory"></property>
        <property name="keySerializer">
            <bean class="org.springframework.data.redis.serializer.StringRedisSerializer"></bean>
        </property>
        <property name="valueSerializer">
            <bean class="org.springframework.data.redis.serializer.StringRedisSerializer"></bean>
        </property>
    </bean>
     */
    @Bean
    public RedisTemplate<String, Object> createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
