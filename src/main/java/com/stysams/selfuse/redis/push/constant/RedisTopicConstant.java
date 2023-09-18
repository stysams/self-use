package com.stysams.selfuse.redis.push.constant;

import lombok.Getter;

@Getter
public enum RedisTopicConstant {

    TEST_REDIS_TOPIC_01("测试RedisTopic01", "testRedisTopic01");

    private final String name;
    private final String code;
    RedisTopicConstant(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
