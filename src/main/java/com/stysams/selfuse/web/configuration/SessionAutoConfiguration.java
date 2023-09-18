package com.stysams.selfuse.web.configuration;

import com.stysams.selfuse.web.config.CookieConfig;
import com.stysams.selfuse.web.config.CookieManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionAutoConfiguration {
    @Bean
    public CookieConfig cookieConfig(){
        return new CookieConfig();
    }
    @Bean
    public CookieManager cookieManager(){
        return new CookieManager();
    }

}
