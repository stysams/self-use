package com.stysams.selfuse.redis.cache.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.lang.reflect.Type;

public class JsonRedisSerializer<T> implements RedisSerializer<T> {
    private final Type type;

    public JsonRedisSerializer(Type type) {
        this.type = type;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return JSONObject.toJSONBytes(t);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        return JSONObject.parseObject(bytes,type);
    }
}
