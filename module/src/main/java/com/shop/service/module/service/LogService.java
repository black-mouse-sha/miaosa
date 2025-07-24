package com.shop.service.module.service;

import com.shop.service.module.entity.LogEntity;
import com.shop.service.module.entity.Result;

public interface LogService {
    Result insertLog(LogEntity logEntity);
}
