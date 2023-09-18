package com.stysams.selfuse.redis.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class StorageService {

    @Cacheable(value = "get_storage", keyGenerator = "selfKeyGenerator")
    //@Cacheable(value = "get_storage", key = "#id")
    public String getStorage(Long id){
        log.info("getStorage id:{}", id);
        return "storage->" + id;
    }
    @CachePut(value = "get_storage", keyGenerator = "selfKeyGenerator")
    public String updateStorage(Long id){
        log.info("updateStorage id:{}", id);
        return "storage-> update:" + id;
    }
    @CacheEvict(value = "get_storage", keyGenerator = "selfKeyGenerator")
    public String clearStorage(Long id){
        log.info("updateStorage id:{}", id);
        return "storage-> clear:" + id;
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    public String setStorage(String content) {
        String res = "hello world";
        redisTemplate.opsForValue().set("storage", content);
        return res;
    }
    public String getStorage(){
        Object res = Objects.requireNonNull(redisTemplate.opsForValue().get("storage"));
        System.out.println(res);
        return res.toString();
    }
}
