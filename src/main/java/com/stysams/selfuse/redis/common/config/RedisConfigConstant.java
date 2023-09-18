package com.stysams.selfuse.redis.common.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
//@EnableConfigurationProperties(RedisConfigConstant.class)
@Component
@ConfigurationProperties(prefix = "spring.data.redis")
@Data
public class RedisConfigConstant {
    private String host;
    private String password;
    private String port;


    @PostConstruct
    public void init(){
        log.info("load redis config:{}", this);
    }

}
