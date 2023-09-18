package com.stysams.selfuse.redis;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.stysams.selfuse.redis.cache.service.StorageService;
import com.stysams.selfuse.redis.push.constant.RedisTopicConstant;
import com.stysams.selfuse.redis.push.listener.RedisTopicListener1;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("set/{content}")
    public String setRedisCache(@PathVariable String content){
        return storageService.setStorage(content);
    }
    @GetMapping("getContent")
    public String get(){
        return storageService.getStorage();
    }


    @GetMapping("get")
    public String get(Integer id){
        return storageService.getStorage(id.longValue());
    }
    @GetMapping("update")
    public String update(Integer id){
        return storageService.updateStorage(id.longValue());
    }
    @GetMapping("clear")
    public String clear(Integer id){
        return storageService.clearStorage(id.longValue());
    }

    @GetMapping("getMapValue")
    @Cacheable(value = "controllerMap", key = "#root.args[0]")
    public Map<String,Object> getMapValue(String key){
        return new JSONObject().fluentPut("name","111").fluentPut("age",30).getInnerMap();
    }

    @Autowired
    private RedisTopicListener1 redisTopicListener1;
    @GetMapping("pushMessage")
    public void pushMessage(String content){
        RTopic topic = redissonClient.getTopic(RedisTopicConstant.TEST_REDIS_TOPIC_01.getCode());
        topic.publish(content);
    }
    @GetMapping("addListener")
    public void addListener(){
        RTopic topic = redissonClient.getTopic(RedisTopicConstant.TEST_REDIS_TOPIC_01.getCode());
        topic.addListener(String.class,redisTopicListener1);
    }
    @GetMapping("removeListener")
    public void removeListener(){
        RTopic topic = redissonClient.getTopic(RedisTopicConstant.TEST_REDIS_TOPIC_01.getCode());
        topic.removeListener(redisTopicListener1);
    }


}
