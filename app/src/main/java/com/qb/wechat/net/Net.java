package com.qb.wechat.net;

import com.qb.wxbase.okhttp.Http;
import com.qb.wxbase.okhttp.base.HttpCallBack;
import com.qb.wxbase.okhttp.base.HttpPostParameterBuilder;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/10
 * 包    名：com.qb.wechat.net
 * 描    述：网络请求类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class Net {

    public static void test(String username,HttpCallBack callBack){
        HttpPostParameterBuilder builder = new HttpPostParameterBuilder();
        builder.add("str",username);

        Http.post(
                Urls.getUrls(Urls.test),
                builder,
                callBack
        );
    }

    public static void testGet(String username, HttpCallBack callBack){
        HttpPostParameterBuilder builder = new HttpPostParameterBuilder();
        builder.add("str",username);
        Http.get(
                Urls.getUrls(Urls.test)+"?str="+username,
                callBack
        );
    }

}
