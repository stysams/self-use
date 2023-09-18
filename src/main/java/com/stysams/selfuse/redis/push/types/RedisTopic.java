package com.stysams.selfuse.redis.push.types;

import com.stysams.selfuse.redis.push.constant.RedisTopicConstant;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface RedisTopic {

    RedisTopicConstant topic() default RedisTopicConstant.TEST_REDIS_TOPIC_01;

}
