package com.qb.wxbase.okhttp;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.service.carrier.CarrierMessagingService;
import android.support.annotation.NonNull;
import android.util.Log;

import com.qb.wxbase.json.Json;
import com.qb.wxbase.okhttp.base.HttpCallBack;
import com.qb.wxbase.okhttp.base.HttpPostParameterBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/10
 * 包    名：com.qb.wxbase.okhttp
 * 描    述：Http请求帮助类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class Http {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    /**
     * Get请求
     * @param url          请求地址
     * @param httpCallBack 请求回调
     */
    public static void get(String url, HttpCallBack httpCallBack) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        httpCallBack.onStart();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                httpCallBack.onDefeated();
                httpCallBack.onFinish(false);
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                httpCallBack.onSuccess(response.body());
                httpCallBack.onFinish(true);
            }
        });
    }

    /**
     * 发送post请求
     * @param url 请求地址
     * @param json 请求json
     * @param httpCallBack 回调接口
     */
    public static void post(String url, String json,HttpCallBack httpCallBack) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        //post主线程
        httpCallBack.onStart();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                httpCallBack.onDefeated();
                httpCallBack.onFinish(false);
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                httpCallBack.onSuccess(response.body());
                httpCallBack.onFinish(true);
            }
        });
    }

    /**
     * 发送post请求
     * @param url 请求地址
     * @param builder 请求参数列表封装
     * @param httpCallBack 回调接口
     */
    public static void post(String url, HttpPostParameterBuilder builder,HttpCallBack httpCallBack) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(builder.builder())
                .build();
        Call call = client.newCall(request);
        Log.d("Http>>>", "post: "+builder.builder().toString());
        //post主线程
        httpCallBack.onStart();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                httpCallBack.onDefeated();
                httpCallBack.onFinish(false);
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                httpCallBack.onSuccess(response.body());
                httpCallBack.onFinish(true);
            }
        });
    }

}
