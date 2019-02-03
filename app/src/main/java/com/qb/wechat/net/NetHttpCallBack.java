package com.qb.wechat.net;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.qb.wxbase.json.Json;
import com.qb.wxbase.okhttp.base.HttpCallBack;
import com.qb.wxbase.okhttp.base.ResponseBean;
import com.qb.wxbase.util.uibase.DialogFor;
import com.qb.wxui.dialog.WaChatDialog;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/10
 * 包    名：com.qb.wxbase.okhttp.base
 * 描    述：默认的Http请求回调封装部分
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public abstract class NetHttpCallBack implements HttpCallBack {
    private String msg = "";
    private Activity activity;
    private Dialog dialog;

    private String titles = "请求失败";

    protected NetHttpCallBack(Activity activity) {
        this.activity = activity;
    }

    protected NetHttpCallBack(Activity activity,String titles) {
        this.activity = activity;
        this.titles = titles;
    }

    @Override
    public void onStart() {
        Log.d("net", "onStart: ");
        activity.runOnUiThread(() -> {
            dialog = WaChatDialog.showLoadingDialog(activity,"请稍候...");
            dialog.setCanceledOnTouchOutside(false);
        });
    }

    @Override
    public void onFinish(boolean isSuccess) {
        Log.d("net", "onFinish: ");
        activity.runOnUiThread(() -> {
            dialog.dismiss();
//            if (!isSuccess) Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        String str = "";
        try {
            str = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String finalStr = str;
        activity.runOnUiThread(() -> {
            Log.d("net", "onSuccess: " + finalStr);
            ResponseBean bean = Json.obj(finalStr, ResponseBean.class);
            if (bean.getCode() == 2000) {
                success(bean.getData());
            } else {
                defeated(bean.getCode(), bean.getMsg());
            }
        });
    }

    @Override
    public void onDefeated() {
        Log.d("net", "onDefeated: ");
        activity.runOnUiThread(()->{
            WaChatDialog.showSystemAffirmDialog(activity,"请求异常","数据请求异常,请检查网络连接!");
        });
    }

    public abstract void success(String data);

    private void defeated(int code, String msg) {
        this.msg = code + ":" + msg;
//        Toast.makeText(activity,this.msg,Toast.LENGTH_SHORT).show();//显示
        WaChatDialog.showSystemAffirmDialog(activity,titles,msg);
    }
}
