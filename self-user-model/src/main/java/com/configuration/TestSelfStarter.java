package com.configuration;

import com.stysams.selfuse.starter.SelfStarterSpringSericveImpl;
import com.stysams.selfuse.starter.SelfStarterSpringService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author StysaMS
 */
@ComponentScan("com.stysa")
@Configuration
public class TestSelfStarter {

    @Bean
    public SelfStarterSpringService initSelfStarterSpringService(){
        return new SelfStarterSpringSericveImpl();
    }
}
