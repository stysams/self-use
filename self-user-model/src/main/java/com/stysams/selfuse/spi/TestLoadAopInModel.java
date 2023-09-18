package com.stysams.selfuse.spi;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestLoadAopInModel {



    @Around("execution(* com.stysams.selfuse.spi.service.TeatLoadService.sout())")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("in model");
        joinPoint.proceed();
        return null;
    }
}
