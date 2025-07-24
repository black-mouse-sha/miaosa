package com.shop.service.user.configuration;

import com.shop.service.module.feign.TestFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HttpInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private TestFeignService testFeignService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将我们自定义拦截器GlobalLogInterceptorHandler添加到全局拦截器中
        InterceptorRegistration registration = registry.addInterceptor(new GlobalLogInterceptorHandler(testFeignService));
        //监控该服务下的所有请求
        registration.addPathPatterns("/**");
    }
}
