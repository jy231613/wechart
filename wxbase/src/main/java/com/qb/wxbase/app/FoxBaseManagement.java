package com.qb.wxbase.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import java.util.HashSet;
import java.util.Set;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cn.secret.base.app
 * 日    期：2018/9/5
 * 包    名：z c app
 * 描    述：Activity管理类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class FoxBaseManagement {
    private BaseActivity nowActivity;
    private Set<Activity> activitySet;
    private FoxBaseManagement(){}
    @SuppressLint("StaticFieldLeak")
    private static FoxBaseManagement foxBaseManagement;
    public static FoxBaseManagement getFoxManagement() {
        if (foxBaseManagement==null)foxBaseManagement=new FoxBaseManagement();
        return foxBaseManagement;
    }

    /**
     * 创建一个Activity
     * @param activity Activity对象
     */
    public void createActivity(Activity activity){
        if (activitySet==null)activitySet=new HashSet<>();
        activitySet.add(activity);
    }

    /**
     * 绑定当前Activity
     * @param activity Activity对象
     */
    public void bindNowActivity(BaseActivity activity){
        nowActivity = activity;
    }

    /**
     * 添加一个Activity在退出以后
     * @param activity activity对象
     */
    public void createActivityAfterExit(Activity activity){
        if (activitySet==null)activitySet=new HashSet<>();
        exit();
        activitySet.add(activity);
    }

    /**
     * 退出一个Activity
     * @param activity Activity对象
     */
    public void exitActivity(Activity activity){
        activitySet.remove(activity);
        activity.finish();
    }

    /**
     * 启动一个Activity
     * @param cls Class
     */
    public void beginActivity(Class cls){
        nowActivity.startActivity(new Intent(nowActivity,cls));
    }

    /**
     * 退出App
     */
    public void exit(){
        if (activitySet==null)activitySet=new HashSet<>();
        for (Activity activity:activitySet) {
            activity.finish();
        }
        activitySet.clear();
    }
}
