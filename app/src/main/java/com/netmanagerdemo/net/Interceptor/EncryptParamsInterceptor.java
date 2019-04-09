package com.netmanagerdemo.net.Interceptor;

import android.text.TextUtils;

import com.netmanagerdemo.net.LogUtil;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by hluo on 2019/4/9.
 */
public class EncryptParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request.Builder requestBuilder = chain.request().newBuilder();
        Charset charset = Charset.forName("UTF-8");
        String method = request.method();
        if (method.endsWith("POST")){
            LogUtil.i("是POST请求");
            FormBody oldFormBody = (FormBody) request.body();
            Buffer buffer = new Buffer();
            oldFormBody.writeTo(buffer);
            String params = buffer.readString(charset);
            LogUtil.i("得到请求参数："+params);
            RequestBody requestBody = null;
            if (TextUtils.isEmpty(params)){
                requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),"");
            }else{
                requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),"{\"data\":\"" + params + "\"}");
            }
            requestBuilder.method(request.method(), requestBody);
        }else{
            LogUtil.i("是GET请求");
        }

        return chain.proceed(requestBuilder.build());
    }
}
