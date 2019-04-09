package com.netmanagerdemo.net;

import com.netmanagerdemo.bean.Sutdent;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by hluo on 2019/4/9.
 */
public interface Request {

    @POST("index.php")
    Observable<HttpStatus<List<Sutdent>>> getStus(@Body RequestBody body);

    @GET("city.php")
    Observable<HttpStatus<String>> getCity();

}
