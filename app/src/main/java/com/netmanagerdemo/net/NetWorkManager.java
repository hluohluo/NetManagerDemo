package com.netmanagerdemo.net;

import android.util.Log;

import com.netmanagerdemo.BaseApplication;
import com.netmanagerdemo.net.Interceptor.CaheInterceptor;
import com.netmanagerdemo.net.Interceptor.CustomHeaderInterceptor;
import com.netmanagerdemo.net.Interceptor.CustomLoggingInerceptor;
import com.netmanagerdemo.net.Interceptor.EncryptParamsInterceptor;
import com.netmanagerdemo.net.Interceptor.NetCacheInterceptor;
import com.netmanagerdemo.net.Interceptor.OfflineCacheInterceptor;
import com.netmanagerdemo.net.converter.CustomGsonConverterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hluo on 2019/4/8.
 */
public class NetWorkManager {
    private static NetWorkManager mInstance;
    private static Retrofit retrofit;
    private static volatile Request request = null;

    public static NetWorkManager getInstance() {
        if (mInstance == null) {
            synchronized (NetWorkManager.class) {
                mInstance = new NetWorkManager();
            }
        }
        return mInstance;
    }

    /**
     * 初始化必要的对象和参数
     */
    public void init() {

        HttpLoggingInterceptor httpLoging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("hao_zz", message);
            }
        });
        httpLoging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //自定义请求日志
        /**
         * 最好放在拦截的最后添加
         */
//        CustomLoggingInerceptor httpLoging = new CustomLoggingInerceptor();

        //公共请求参数拦截
        CustomHeaderInterceptor headerInterceptor = new CustomHeaderInterceptor();

        //缓存策略设置拦截，缓存只对get请求有效果，对post请求无效果
        //设置 请求的缓存的大小跟位置
        File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小
//        CaheInterceptor caheInterceptor = new CaheInterceptor(BaseApplication.getAppContext());

        NetCacheInterceptor netCacheInterceptor = new NetCacheInterceptor();
        OfflineCacheInterceptor offlineCacheInterceptor = new OfflineCacheInterceptor();

        //参数加密拦截
        /**
         * 可通过RequestBody来解决
         */
        EncryptParamsInterceptor encryptParamsInterceptor = new EncryptParamsInterceptor();


        //结果解密拦截


        //初始化okhttp
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
//                .addNetworkInterceptor(netCacheInterceptor)
//                .addInterceptor(offlineCacheInterceptor)
//                .cache(cache)
                .addInterceptor(httpLoging)
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .build();


        //初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://192.168.2.18/server/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .build();
    }

    /**
     * 初始化网络请求接口
     *
     * @return
     */
    public static Request getRequest() {
        if (request == null) {
            synchronized (Request.class) {
                request = retrofit.create(Request.class);
            }
        }
        return request;
    }

}
