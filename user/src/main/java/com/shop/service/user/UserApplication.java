package com.shop.service.user;

import com.shop.service.module.util.BannerBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.shop.service"})
public class UserApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(UserApplication.class);
        springApplication.setBanner(new BannerBuilder());
        springApplication.run(args);
    }

}
