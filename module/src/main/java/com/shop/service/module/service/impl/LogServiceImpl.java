package com.shop.service.module.service.impl;

import com.shop.service.module.entity.LogEntity;
import com.shop.service.module.entity.Result;
import com.shop.service.module.mapper.LogMapper;
import com.shop.service.module.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public Result insertLog(LogEntity logEntity) {

        int updateCount = logMapper.insert(logEntity);
        if(updateCount>0){
            return Result.end(200,"","本地插入日志成功");
        }else{
            return Result.end(500,"","本地插入日志失败");
        }
    }
}
