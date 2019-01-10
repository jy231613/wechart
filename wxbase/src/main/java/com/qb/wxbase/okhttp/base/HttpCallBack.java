package com.qb.wxbase.okhttp.base;

import okhttp3.ResponseBody;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/10
 * 包    名：com.qb.wxbase.okhttp
 * 描    述：网络请求回调接口
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public interface HttpCallBack {
    void onStart();
    void onFinish(boolean isSuccess);
    void onSuccess(ResponseBody responseBody);
    void onDefeated();
}
