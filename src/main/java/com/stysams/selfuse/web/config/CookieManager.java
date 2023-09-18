package com.stysams.selfuse.web.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CookieManager {


    private CookieConfig cookieConfig;

    @Getter
    private HttpServletResponse response;

    @Autowired
    public void setCookieConfig(CookieConfig cookieConfig) {
        this.cookieConfig = cookieConfig;
    }

    @Autowired
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @SneakyThrows
    public void setCookie(String key,String value){
        Cookie cookie = new Cookie(key, URLEncoder.encode(value, StandardCharsets.UTF_8));
        cookie.setMaxAge(cookieConfig.getExpiry());
        cookie.setPath(cookieConfig.getPath());
        response.addCookie(cookie);
    }
}
