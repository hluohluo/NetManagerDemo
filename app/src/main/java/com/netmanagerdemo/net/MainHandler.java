package com.netmanagerdemo.net;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by hluo on 2019/4/9.
 */
public class MainHandler extends Handler {

    private static volatile MainHandler instance;

    public static MainHandler getInstance() {
        if (null == instance) {
            synchronized (MainHandler.class) {
                if (null == instance) {
                    instance = new MainHandler();
                }
            }
        }
        return instance;
    }

    private MainHandler() {
        super(Looper.getMainLooper());
    }
}
