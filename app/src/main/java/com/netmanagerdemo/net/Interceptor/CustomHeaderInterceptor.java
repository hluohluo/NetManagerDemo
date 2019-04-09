package com.netmanagerdemo.net.Interceptor;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hluo on 2019/4/9.
 */
public class CustomHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("token","jfkdsjfksdjfs")
                .build();
        return chain.proceed(request);
    }
}
