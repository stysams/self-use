package com.stysams.selfuse.redis.bloom.service;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloomFilterService {

    @Autowired
    private RedissonClient redissonClient;

    public RBloomFilter<String> createBloom(String name){
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(name);
        bloomFilter.tryInit(1000, 0.005);
        return bloomFilter;
    }

}
