package com.stysams.selfuse.web.configuration;

import com.stysams.selfuse.web.file.UploadProp;
import com.stysams.selfuse.web.interceptor.FileUploadInterceptor;
import com.stysams.selfuse.web.interceptor.LogInterceptor;
import com.stysams.selfuse.web.util.SpringContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/**/*.*");
        registry.addInterceptor(fileUploadInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/**/*.*");
    }

    @Bean
    public UploadProp uploadProp() {
        return new UploadProp();
    }
    @Bean
    @ConditionalOnMissingBean
    public FileUploadInterceptor fileUploadInterceptor() {
        return new FileUploadInterceptor();
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
