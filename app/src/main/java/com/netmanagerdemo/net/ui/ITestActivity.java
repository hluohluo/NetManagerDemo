package com.netmanagerdemo.net.ui;

import android.content.Intent;
import android.view.View;

import com.netmanagerdemo.R;
import com.netmanagerdemo.base.BaseActivity;
import com.netmanagerdemo.net.LogUtil;
import com.netmanagerdemo.net.ui.presenter.ITestView;
import com.netmanagerdemo.net.ui.presenter.TestPresenter;

import butterknife.OnClick;

/**
 * Created by hluo on 2019/4/10.
 */
public class ITestActivity extends BaseActivity<TestPresenter> implements ITestView {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getIntent(Intent intent) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.btn,R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                mPresenter.getData();
                break;
            case R.id.btn2:

                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showUI(String info) {
        LogUtil.i("获得请求到的数据为："+info);
    }

    @Override
    protected TestPresenter createPresenter() {
        return new TestPresenter();
    }
}
