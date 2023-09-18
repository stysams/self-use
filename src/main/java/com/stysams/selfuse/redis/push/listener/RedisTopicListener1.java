package com.stysams.selfuse.redis.push.listener;

import com.stysams.selfuse.redis.push.constant.RedisTopicConstant;
import com.stysams.selfuse.redis.push.types.RedisTopic;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.listener.MessageListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RedisTopic(topic = RedisTopicConstant.TEST_REDIS_TOPIC_01)
public class RedisTopicListener1 implements MessageListener<String> {

    @Override
    public void onMessage(CharSequence channel, String msg) {
        log.info("监听消息(Redis 发布/订阅): {}", msg);
    }

}
