package com.netmanagerdemo.net;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.netmanagerdemo.BaseApplication;
import com.netmanagerdemo.base.IBaseView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by hluo on 2019/4/9.
 */
public abstract class BaseObserver<T> implements Observer<HttpStatus<T>> {

    private IBaseView mView;

    private boolean isShowDialog = true;

    public BaseObserver() {
    }

    public BaseObserver(IBaseView mView) {
        this.mView = mView;
    }

    public BaseObserver(IBaseView mView, boolean isShowDialog) {
        this.mView = mView;
        this.isShowDialog = isShowDialog;
    }


    @Override
    public void onSubscribe(Disposable d) {
        LogUtil.i("请求开始");
        showLoadingDialog();
        if (!NetworkUtils.isConnected(BaseApplication.getAppContext())) {
            Toast.makeText(BaseApplication.getAppContext(), "网络异常，请检测你的网络", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNext(HttpStatus<T> tHttpStatus) {
        LogUtil.i("请求成功");
        if (tHttpStatus.getCode() == 0) {
            onSuccess(tHttpStatus.getData());
        } else {
            onFailure(tHttpStatus.getCode(), tHttpStatus.getMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        hideLoadingDialog();
        if (e instanceof ApiException) {
            LogUtil.i("服务器返回错误");
            ApiException apiException = (ApiException) e;
            onFailure(apiException.getmErrorCode(), apiException.getmErrorMessage());
            if (apiException.isTokenExpried()) {
                MainHandler.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        exitDialog();
                    }
                });
            }
        } else {
            LogUtil.e("未知错误：" + e);
        }
    }

    /**
     * * 请求成功了才会调用 onComplete
     * onError 时不会调用
     */
    @Override
    public void onComplete() {
        LogUtil.i("请求结束");
        hideLoadingDialog();
    }

    /**
     * 请求成功返回
     *
     * @param result 服务器返回数据
     */
    public abstract void onSuccess(T result);

    /**
     * 请求失败返回
     *
     * @param errMsg    失败信息
     * @param errorCode 错误码
     */
    public abstract void onFailure(int errorCode, String errMsg);


    /**
     * 显示 LoadingDialog
     */
    private void showLoadingDialog() {
        if (isShowDialog && mView != null) {
            mView.showLoading();
        }
    }

    /**
     * 隐藏 Loading
     */
    private void hideLoadingDialog() {
        if (isShowDialog && mView != null) {
            mView.hideLoading();
        }
    }


    private void exitDialog() {
        // 创建构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseApplication.get().currentActivity());
        builder.setTitle("Token失效")
                .setMessage("请重新登陆账号")
                .create()
                .show();

    }
}
