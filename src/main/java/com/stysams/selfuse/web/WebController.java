package com.stysams.selfuse.web;

import com.stysams.selfuse.web.config.CookieManager;
import lombok.SneakyThrows;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("web")
public class WebController {

    @Autowired
    private CookieManager cookieManager;

    @SneakyThrows
    @GetMapping("setCookie")
    public void setCookie(String content){
        if("111".equals(content)){
            Thread.sleep(3000);
        }
        System.out.println(cookieManager.getResponse());
        cookieManager.setCookie("key",content);
        cookieManager.getResponse().sendRedirect("sout?content=" + content);
    }

    @GetMapping("sout")
    public void sout(String content){
        MDC.put("traceId", "1234567890");
        ThreadContext.put("traceId", "1234567890");
        System.out.println(content);
    }


}
