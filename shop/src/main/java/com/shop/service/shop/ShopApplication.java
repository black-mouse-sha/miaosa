package com.shop.service.shop;

import com.shop.service.module.util.BannerBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.shop.service"})
public class ShopApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(ShopApplication.class);
        springApplication.setBanner(new BannerBuilder());
        springApplication.run(args);
    }

}
