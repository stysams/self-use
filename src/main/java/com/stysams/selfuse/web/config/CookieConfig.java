package com.stysams.selfuse.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "project.cookie")
public class CookieConfig {

    /**
     * cookie path 默认 "/"
     */
    private String path = "/";

    /**
     * cookie 过期时间 默认与浏览器生命周期一直
     */
    private int expiry = -1;
}
