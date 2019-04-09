package com.netmanagerdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.netmanagerdemo.net.NetWorkManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.Stack;

/**
 * Created by hluo on 2019/4/8.
 */
public class BaseApplication extends Application {

    private static Context mContext;
    private static BaseApplication instance;
    public Stack<Activity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        instance = this;
        initLogger();
        initActivityStack();
        NetWorkManager.getInstance().init();
    }

    public static BaseApplication get(){
        return instance;
    }

    public static Context getAppContext() {
        return mContext;
    }

    private void initLogger() {
        PrettyFormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(3)
                .tag("hao_zz")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    private void initActivityStack() {
        activityStack = new Stack<>();
        registerActivityLifecycleCallbacks(new ActivityCallbacks());
    }

    /**
     * 获得当前的activity(即最上层)
     *
     * @return
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (!activityStack.empty())
            activity = activityStack.lastElement();
        return activity;
    }


    /**
     * 结束除cls之外的所有activity,执行结果都会清空Stack
     *
     * @param cls
     */
    public void finishAllActivityExceptOne(Class<? extends Activity> cls) {
        while (!activityStack.empty()) {
            Activity activity = currentActivity();
            if (activity.getClass().equals(cls)) {
                activityStack.remove(activity);
            } else {
                activity.finish();
                activityStack.remove(activity);
                activity = null;
            }
        }
    }



    private class ActivityCallbacks implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            activityStack.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            activityStack.remove(activity);
        }
    }

}
