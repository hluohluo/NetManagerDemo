package com.netmanagerdemo.net;

import android.util.Log;

/**
 * Created by hluo on 2019/4/9.
 */
public class LogUtil {

    private static final String TAG = "hao_zz";

    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }
}
