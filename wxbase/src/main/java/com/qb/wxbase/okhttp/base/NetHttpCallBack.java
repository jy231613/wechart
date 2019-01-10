package com.qb.wxbase.okhttp.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.qb.wxbase.json.Json;
import com.qb.wxbase.util.uibase.DialogFor;

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
    private ProgressDialog dialog;

    protected NetHttpCallBack(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onStart() {
        Log.d("net", "onStart: ");
        activity.runOnUiThread(() -> {
            dialog = DialogFor.getWaitDialog(activity, "请求中...");
            dialog.onStart();
        });
    }

    @Override
    public void onFinish(boolean isSuccess) {
        Log.d("net", "onFinish: ");
        activity.runOnUiThread(() -> {
            dialog.dismiss();
            if (!isSuccess) Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
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
            if (bean.getCode() == 200) {
                success(bean.getData());
            } else {
                defeated(bean.getCode(), bean.getMsg());
            }
        });
    }

    @Override
    public void onDefeated() {
        Log.d("net", "onDefeated: ");
    }

    public abstract void success(String data);

    private void defeated(int code, String msg) {
        this.msg = code + ":" + msg;
    }
}
