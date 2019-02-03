package com.qb.wxbase.coze.model;

import com.qb.wxbase.coze.base.SocketConfig;
import com.qb.wxbase.coze.bean.SocketHeader;
import com.qb.wxbase.util.baseutil.StrUtil;

import java.util.Date;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/28
 * 包    名：com.qb.wxbase.coze.model
 * 描    述：
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public class SocketSend {
    public static SocketHeader sendSocketHeader(String userId){
        SocketHeader socketHeader = new SocketHeader();
        //socketHeader初始化
        socketHeader.setPhoneName(SocketConfig.configModel.getPhoneName());
        socketHeader.setRequestIp(SocketConfig.configModel.getMyIp());
        socketHeader.setRequestKey(StrUtil.getUUID(true));
        socketHeader.setSendTime(new Date(System.currentTimeMillis()));
        socketHeader.setUserId(userId);
        return socketHeader;
    }
}
