package com.stysams.selfuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// 启用 AOP
@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = {"com.configuration"})
//@SpringBootApplication

public class SelfUseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfUseApplication.class, args);
        System.out.println("start success!");
    }

}
