package com.stysams.selfuse;

import com.configuration.TestSelfStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

// 启用 AOP
@EnableAspectJAutoProxy
//@SpringBootApplication(scanBasePackages = {"com.configuration"})
@SpringBootApplication
//@Import(TestSelfStarter.class)
public class SelfUseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfUseApplication.class, args);
        System.out.println("start success!");
    }

}
