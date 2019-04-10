package com.netmanagerdemo.net.model;

import com.netmanagerdemo.bean.Sutdent;
import com.netmanagerdemo.net.BaseRequestBody;
import com.netmanagerdemo.net.HttpStatus;
import com.netmanagerdemo.net.NetWorkManager;
import com.netmanagerdemo.net.RxSchedulers;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by hluo on 2019/4/10.
 */
public class StudentModel {

    public Observable<HttpStatus<List<Sutdent>>> getStudents(){
        Map map = BaseRequestBody.getMap();
        map.put("userId",123456);
        return NetWorkManager.getRequest().getStus(BaseRequestBody.createEncryptBody(map))
                .compose(RxSchedulers.<HttpStatus<List<Sutdent>>>applySchedulers());
    }
}
