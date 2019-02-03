package com.qb.wxbase.coze.listener;

import com.qb.wxbase.coze.model.SocketTransmit;

import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/21
 * 包    名：com.qb.wxbase.coze.listener
 * 描    述：接收消息接口
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public interface ReceiveMessage {
    //接收点对点消息
    void receiveMessage(SocketTransmit socketTransmit);
    //接收事件
    void receiveEvent(SocketTransmit socketTransmit);
    //接收状态
    void receiveStatus(SocketTransmit socketTransmit);
    //接收系统消息
    void receiveSystem(SocketTransmit socketTransmit);
    //接收群组消息
    void receiveGroup(SocketTransmit socketTransmit);
    //接收缓存消息
    void receiveCatch(List<SocketTransmit> socketTransmits);
}
