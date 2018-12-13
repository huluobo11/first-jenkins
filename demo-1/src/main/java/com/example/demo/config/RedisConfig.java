package com.example.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  redis缓存
 *
 * Created by ybl on 2018/11/14.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 缓存管理器
     * @param jedisConnectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager (JedisConnectionFactory redisConnectionFactory){
        return RedisCacheManager.create(redisConnectionFactory);
    }

    /**
     * RedisTemplate配置
     * @param jedisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String,String> stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        // 配置redisTemplate
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);// key 序列化
        redisTemplate.setValueSerializer(stringSerializer);//value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        // 设置序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper()
                .setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY)
                .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 配置redisTemplate
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);// key 序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);//value序列化
        redisTemplate.setHashKeySerializer(stringSerializer);//Hash key 序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);//Hash value 序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    
    @Bean
    public ValueOperations<String, String> valOpsStr(RedisTemplate<String, String> stringRedisTemplate){
    	return stringRedisTemplate.opsForValue();
    }
    @Bean
    public ValueOperations<Object, Object> valOpsObj(RedisTemplate<Object, Object> redisTemplate){
    	return redisTemplate.opsForValue();
    }
    
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
    	return new JedisConnectionFactory();
    }
}

