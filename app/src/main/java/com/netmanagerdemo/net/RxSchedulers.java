package com.netmanagerdemo.net;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hluo on 2019/4/10.
 * Rx线程相关的切换
 */
public class RxSchedulers {
    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T,T> applySchedulers(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
