package com.shop.service.module.feign;

import com.shop.service.module.entity.LogEntity;
import com.shop.service.module.feign.fallback.TestFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "log-service",fallback = TestFeignServiceImpl.class)
public interface TestFeignService {

    @GetMapping("/feign/hello")
    public String hello();

    @PutMapping("/feign/insert")
    public String insert(@RequestBody LogEntity logEntity);

    @PostMapping("/insert")
    String insertLog(@RequestBody() LogEntity logEntity);


}
