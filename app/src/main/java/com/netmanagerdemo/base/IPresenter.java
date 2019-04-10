package com.netmanagerdemo.base;

/**
 * Created by hluo on 2019/4/10.
 */
public interface IPresenter<V extends IBaseView> {
    /**
     * 绑定 View
     */
    void attachView(V mView);

    /**
     * 解除 View
     */
    void detachView();
}
