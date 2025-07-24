package com.shop.service.oauth;

import com.shop.service.module.util.BannerBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@MapperScan("com.shop.service.oauth.mapper")
@EnableResourceServer
@SpringBootApplication
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(OauthApplication.class);
        springApplication.setBanner(new BannerBuilder());
        springApplication.run(args);
    }

}
