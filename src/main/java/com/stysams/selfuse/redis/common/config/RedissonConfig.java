package com.stysams.selfuse.redis.common.config;

import com.stysams.selfuse.redis.push.types.RedisTopic;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Autowired
    private RedisConfigConstant redisConfigConstant;


    @Bean
    public RedissonClient redissonClient(ConfigurableApplicationContext applicationContext){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisConfigConstant.getHost() + ":" +
                redisConfigConstant.getPort()).setPassword(redisConfigConstant.getPassword());
        RedissonClient redissonClient = Redisson.create(config);

        String[] beanNamesForType = applicationContext.getBeanNamesForType(MessageListener.class);
        for (String beanName : beanNamesForType) {
            MessageListener bean = applicationContext.getBean(beanName, MessageListener.class);

            Class<? extends MessageListener> beanClass = bean.getClass();

            if (beanClass.isAnnotationPresent(RedisTopic.class)) {
                RedisTopic redisTopic = beanClass.getAnnotation(RedisTopic.class);

                RTopic topic = redissonClient.getTopic(redisTopic.topic().getCode());
                topic.addListener(String.class, bean);

                ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
                beanFactory.registerSingleton(redisTopic.topic().getCode(), topic);
            }
        }
        return redissonClient;
    }

}
