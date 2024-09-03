package com.android.china.utils;

import android.app.Application;
import android.content.Context;

/**
 * @Author Crwei
 * date 2023/3/14 18:27
 * 全局获得上下文 只需要调用 MyApplication.getContext()方法即可
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    /**
     * 获取全局上下文*/
    public static Context getContext() {
        return context;
    }
}
