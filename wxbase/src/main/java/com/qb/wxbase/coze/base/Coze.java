package com.qb.wxbase.coze.base;

import android.content.Context;

import com.qb.wxbase.coze.base.SocketConfig;
import com.qb.wxbase.coze.listener.ReceiveMessage;
import com.qb.wxbase.util.apkutil.SystemUtils;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/21
 * 包    名：com.qb.wxbase.coze
 * 描    述：
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public class Coze {

    public static void init(Context context){
        //读取配置文件
        SocketConfig.readerConfig(context);
        //获取本地属性
        SocketConfig.configModel.setPhoneName(android.os.Build.MANUFACTURER);
        SocketConfig.configModel.setMyIp(SystemUtils.getIP(context));
    }

}
