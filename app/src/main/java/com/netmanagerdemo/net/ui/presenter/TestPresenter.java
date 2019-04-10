package com.netmanagerdemo.net.ui.presenter;

import com.netmanagerdemo.base.BasePresenter;
import com.netmanagerdemo.bean.Sutdent;
import com.netmanagerdemo.net.BaseObserver;
import com.netmanagerdemo.net.model.StudentModel;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by hluo on 2019/4/10.
 */
public class TestPresenter extends BasePresenter<ITestView> {

    private StudentModel studentModel;
    public TestPresenter() {
        studentModel = new StudentModel();
    }

    public void getData(){
        studentModel.getStudents()
                .subscribe(new BaseObserver<List<Sutdent>>(getView()){
                    @Override
                    public void onSuccess(List<Sutdent> result) {
                        Logger.i("得到结果："+result.toString());
                        getView().showUI(result.toString());
                    }

                    @Override
                    public void onFailure(int errorCode, String errMsg) {
                        Logger.i("得到的错误："+errorCode+","+errMsg);
                    }
                });
    }
}
