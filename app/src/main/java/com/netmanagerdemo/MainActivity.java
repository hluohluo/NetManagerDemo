package com.netmanagerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.netmanagerdemo.bean.Sutdent;
import com.netmanagerdemo.net.BaseObserver;
import com.netmanagerdemo.net.HttpStatus;
import com.netmanagerdemo.net.NetWorkManager;
import com.netmanagerdemo.net.model.StudentModel;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    public Button btn;
    @BindView(R.id.btn2)
    public Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCity();
            }
        });
    }


    private void getData() {
        StudentModel studentModel = new StudentModel();
        studentModel.getStudents()
                .subscribe(new BaseObserver<List<Sutdent>>(){
                    @Override
                    public void onSuccess(List<Sutdent> result) {
                        Logger.i("得到结果："+result.toString());
                    }

                    @Override
                    public void onFailure(int errorCode, String errMsg) {
                        Logger.i("得到的错误："+errorCode+","+errMsg);
                    }
                });

//                .subscribe(new Consumer<HttpStatus<List<Sutdent>>>() {
//                    @Override
//                    public void accept(HttpStatus<List<Sutdent>> listHttpStatus) throws Exception {
//                        Logger.i("获得的值：" + listHttpStatus.getData().toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Logger.i("获得错误信息：" + throwable);
//                        if (throwable instanceof ApiException){
//                            ApiException apiException = (ApiException)throwable;
//                            if (apiException.isTokenExpried()){
//                                MainHandler.getInstance().post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        exitDialog();
//                                    }
//                                });
//                            }
//                        }
//                    }
//                });

    }

    private void getCity() {
        NetWorkManager.getRequest().getCity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HttpStatus<String>>() {
                    @Override
                    public void accept(HttpStatus<String> stringHttpStatus) throws Exception {
                        Logger.i(stringHttpStatus.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.i("获得错误信息：" + throwable);
                    }
                });

    }

}
