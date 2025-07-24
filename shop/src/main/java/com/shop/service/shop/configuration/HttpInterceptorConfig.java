package com.shop.service.shop.configuration;


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
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new GlobalLogInterceptorHandler(testFeignService));
        registration.addPathPatterns("/**");//所有路径都被拦截

    }
}
