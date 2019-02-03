package com.qb.wechat.net;

import android.util.Log;

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

    /**
     * 用户登录
     * @param type 0手机号登录,1嗡嗡号登录
     * @param phone 手机号/嗡嗡号
     * @param password 密码
     * @param callBack 回调接口
     */
    public static void login(int type,String phone,String password,HttpCallBack callBack){
        HttpPostParameterBuilder builder = new HttpPostParameterBuilder();
        builder.add("type",type);
        builder.add("phone",phone);
        builder.add("password",password);
        Http.post(Urls.getUrls(Urls.USER_LOGIN), builder, callBack);
    }

    /**
     * 注册
     * @param nickname 昵称
     * @param userPic 用户头像,可以为空
     * @param phone 手机号
     * @param password 密码
     * @param callBack 回调接口
     */
    public static void register(String nickname,String userPic,String phone,String password,HttpCallBack callBack){
        HttpPostParameterBuilder builder = new HttpPostParameterBuilder();
        builder.add("userPic",userPic);
        builder.add("nickname",nickname);
        builder.add("phone",phone);
        builder.add("password",password);
        Http.post(Urls.getUrls(Urls.USER_REGISTER), builder, callBack);
    }

}
