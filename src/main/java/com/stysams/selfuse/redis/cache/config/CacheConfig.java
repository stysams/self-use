package com.stysams.selfuse.redis.cache.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ReflectionUtils;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private ApplicationContext applicationContext;

    // SpringCache缓存的序列化处理
    @Bean
    public CacheManager cacheManager() {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(120L)) // 默认缓存20秒钟
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JsonRedisSerializer<>(Object.class)))
                .computePrefixWith(name -> name + ":"); // 单冒号
        //return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).withInitialCacheConfigurations(buildInitCaches()).build();
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
    }

    private Map<String, RedisCacheConfiguration> buildInitCaches() {
        HashMap<String, RedisCacheConfiguration> cacheConfigMap = new HashMap<>();
        Arrays.stream(applicationContext.getBeanNamesForType(Object.class))
                .map(applicationContext::getType).filter(Objects::nonNull)
                .forEach(clazz -> {
                            ReflectionUtils.doWithMethods(clazz, method -> {
                                method.setAccessible(true);
                                ReflectionUtils.makeAccessible(method);
                                Cacheable cacheable = AnnotationUtils.findAnnotation(method, Cacheable.class);
                                if (Objects.nonNull(cacheable)) {
                                    for (String cache : cacheable.cacheNames()) {
                                        RedisSerializationContext.SerializationPair<Object> sp = RedisSerializationContext.SerializationPair
                                                .fromSerializer(new JsonRedisSerializer<>(method.getGenericReturnType()));
                                        cacheConfigMap.put(cache, RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(sp));
                                    }
                                }
                            });
                        }
                );
        return cacheConfigMap;
    }
}
