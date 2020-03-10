package com.kk.novel.common.responseDoMain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 用于响应，返回信息
 */
@Data
public class ResponseDoMain implements Serializable {

    private String message;
    private Boolean isSuccess;
    private Object data;
    private Integer code;

    private ResponseDoMain(String message, Boolean isSuccess, Object data, Integer code) {
        this.message = message;
        this.isSuccess = isSuccess;
        this.data = data;
        this.code = code;
    }

    public static ResponseDoMain success (Object data) {
        return new ResponseDoMain("请求成功", true, data, 200);
    }

    public static ResponseDoMain success (Object data, String message) {
        return new ResponseDoMain(message, true, data, 200);
    }

    public static ResponseDoMain failure (Object data) {
        return new ResponseDoMain("请求失败", false, data, 500);
    }

    public static ResponseDoMain failure (Object data, String message) {
        return new ResponseDoMain(message, false, data, 500);
    }

    public static ResponseDoMain tokenExpired () {
        return new ResponseDoMain("登录已过期", false, "", 400);
    }

    public static ResponseDoMain custom (String message, Boolean b , Object data, Integer code) {
        return new ResponseDoMain(message, b, data, code);
    }

}
