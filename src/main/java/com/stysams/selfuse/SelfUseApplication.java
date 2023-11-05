package com.stysams.selfuse;

import com.stysa.configuration.SelfWebsocketStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

// 启用 AOP
@EnableAspectJAutoProxy
//@SpringBootApplication(scanBasePackages = {"com.configuration"})
@SpringBootApplication
@Import(SelfWebsocketStarter.class)
public class SelfUseApplication {

    public static void main(String[] args) {
        //System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(SelfUseApplication.class, args);
        System.out.println("start success!");
    }

}
