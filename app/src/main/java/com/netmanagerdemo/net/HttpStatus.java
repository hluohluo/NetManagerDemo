package com.netmanagerdemo.net;

/**
 * Created by hluo on 2019/4/9.
 */
public class HttpStatus<T> {
    private int code; //返回的code值
    private T data; //返回的数据结果
    private String msg; //返回的接口说明

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
