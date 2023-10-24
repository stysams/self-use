package com.stysa.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author StysaMS
 */
@ComponentScan({"com.stysa"})
@Configuration
@EnableConfigurationProperties(TestSlefConfig.class)
public class TestSelfStarter {

    ////@Autowired
    ////private
    //@Bean
    //public SelfStarterSpringService initSelfStarterSpringService(TestSlefConfig testSlefConfig){
    //    return new SelfStarterSpringSericveImpl(testSlefConfig.name);
    //}
}
