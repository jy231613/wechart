package com.qb.wxbase.coze.listener;

import com.qb.wxbase.coze.model.SocketTransmit;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/21
 * 包    名：com.qb.wxbase.coze.listener
 * 描    述：发送消息之后监听
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public interface SendMessageAfter {
    void afterSend(SocketTransmit socketTransmit);
}
