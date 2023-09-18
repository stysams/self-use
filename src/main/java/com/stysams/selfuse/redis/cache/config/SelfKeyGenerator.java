package com.stysams.selfuse.redis.cache.config;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
@Slf4j
@Component
public class SelfKeyGenerator implements KeyGenerator {
    private static final String  KEY_PREFIX = "storage_" ;

    /**
     * 光根据该逻辑生成redis的key
     * @param target
     * @param method
     * @param params
     * @return
     */
    @NonNull
    @Override
    public Object generate(@NonNull Object target, @NonNull Method method, Object... params) {
        log.info("keyGenerator target:{}",target);
        log.info("keyGenerator method:{}",method);
        log.info("keyGenerator params:{}",params);
        StringBuilder sb = new StringBuilder() ;
        for (Object param : params) {
            sb.append(param) ;
        }
        return KEY_PREFIX + sb;
    }
}
