package com.netmanagerdemo.base;

/**
 * Created by hluo on 2019/4/10.
 */
public interface IBaseView {
    //显示loading
    void showLoading();

    //隐藏loading
    void hideLoading();

    //显示吐司
    void showError(String msg);
}
