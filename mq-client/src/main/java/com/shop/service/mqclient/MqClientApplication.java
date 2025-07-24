package com.shop.service.mqclient;

import com.shop.service.module.util.BannerBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.shop.service"})
public class MqClientApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(MqClientApplication.class);
        springApplication.setBanner(new BannerBuilder());
        springApplication.run(args);
    }

}
