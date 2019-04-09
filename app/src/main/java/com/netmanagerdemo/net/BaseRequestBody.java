package com.netmanagerdemo.net;

import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by hluo on 2019/4/9.
 */
public class BaseRequestBody {

    /**
     * 创建加密请求体
     */
    public static RequestBody createEncryptBody(Map params){
        String enJson = GsonProvider.getGson().toJson(params);
        Logger.i("请求参数："+enJson);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),enJson);
    }

    /**
     * 创建普通请求体
     */
    public static RequestBody createBogy(Map params){
        String jsonInfo = "";
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonInfo);
    }

    public static HashMap getMap(){
        return new HashMap();
    }

}
