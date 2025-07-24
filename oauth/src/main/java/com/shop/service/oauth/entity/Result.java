package com.shop.service.oauth.entity;

import lombok.Data;

@Data
public class Result {

    private int code;
    private Object data;
    private String msg;
    public Result(int code,Object data,String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    public static Result end(int code,Object data,String msg){
        return new Result(code,data,msg);
    }
}
