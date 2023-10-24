package com.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "self")
public class TestSlefConfig {
    public String name;

    public void setName(String name){
        this.name = name;
    }
}
