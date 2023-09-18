package com.stysams.selfuse.spi.aop;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestLoadAop {


    @SneakyThrows
    @Around("execution(* com.stysams.selfuse.spi.service.TeatLoadService.sout())")
    public Object around(ProceedingJoinPoint joinPoint){
        System.out.println(111);
        joinPoint.proceed();
        return null;
    }
}
