package com.shop.service.module.feign.fallback;

import com.alibaba.fastjson.JSONObject;
import com.shop.service.module.entity.LogEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.feign.TestFeignService;
import com.shop.service.module.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestFeignServiceImpl implements TestFeignService {


    @Autowired
    private LogService logService;

    @Override
    public String hello() {
        return "log-service暂时访问不通";
    }

    @Override
    public String insert(LogEntity logEntity) {
        JSONObject res = new JSONObject();
        res.put("code",500);
        res.put("data",logEntity);
        res.put("msg","log-service暂时访问不通");
        return res.toJSONString();
    }

    @Override
    public String insertLog(LogEntity logEntity) {
        System.err.println("远程日志写入失败");
        System.err.println("本地处理写入日志业务");
        Result res =  logService.insertLog(logEntity);
        System.out.println(res);
        System.err.println("本地日志写入完毕");
        return JSONObject.toJSONString(res);
    }
}
