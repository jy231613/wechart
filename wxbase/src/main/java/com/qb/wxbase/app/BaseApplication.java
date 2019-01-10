package com.qb.wxbase.app;

import android.app.Application;

/**
 * Summarize:基础的Application类,提供了一个静态的全局getApplication()方法
 * User:贾恒飞
 * Date:2018/5/9
 * Time:19:00
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */

public class BaseApplication extends Application{
    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static BaseApplication getApplication() {
        return application;
    }
}
