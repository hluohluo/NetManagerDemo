package com.netmanagerdemo.base;

import android.app.Activity;

import com.netmanagerdemo.net.Preconditions;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by hluo on 2019/4/10.
 */
public class BasePresenter<V extends IBaseView> implements IPresenter<V> {

    private V mView;

    private CompositeDisposable compositeDisposable;

    /**
     * 绑定 View
     * @param mView
     */
    @Override
    public void attachView(V mView) {
        this.mView = mView;
    }

    public V getView() {
        Preconditions.checkNotNull(mView, "%s cannot be null", IBaseView.class.getName());
        return mView;
    }

    /**
     * 解除绑定
     */
    @Override
    public void detachView() {
        unDispose();
        mView = null;
    }


    /**
     * 将 {@link Disposable} 添加到 {@link CompositeDisposable} 中统一管理
     * 可在 {@link Activity#onDestroy()} 中使用 {@link #unDispose()} 停止正在执行的 RxJava 任务，避免内存泄漏(框架已自行处理)
     *
     * @param disposable
     */
    public void addDispose(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);// 将所有 Disposable 放入集中处理
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();// 保证 Activity 结束时取消所有正在执行的订阅
        }
    }


    protected <T> LifecycleProvider<T> getLifecycleProvider() {
        LifecycleProvider<T> provider = null;
        if (null != mView && mView instanceof LifecycleProvider) {
            provider = (LifecycleProvider<T>) mView;
        }
        return provider;
    }
}
