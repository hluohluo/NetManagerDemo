package com.netmanagerdemo.net.Interceptor;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by hluo on 2019/4/9.
 */
public class CustomLoggingInerceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody requestBody = request.body();
        Charset charset = Charset.forName("UTF-8");
        Buffer buffer = new Buffer();
        if (requestBody != null) {
            requestBody.writeTo(buffer);
        }
        String infoLog = "----------------------request start---------------------------------\n";
        String method = request.method();
        String url = request.url().toString();
        String params = buffer.readString(charset);
        String headers = request.headers().toString().trim();
        infoLog = infoLog+"method:"+method+"\n"
                +"url:"+url+"\n"
                +"headers:"+headers+"\n"
                +"params:"+params+"\n";

//        Log.e("hao_zz",infoLog);
        Logger.e(infoLog);

        return chain.proceed(request);
    }
}
