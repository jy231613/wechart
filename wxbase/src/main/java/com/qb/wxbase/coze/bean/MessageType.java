package com.qb.wxbase.coze.bean;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/25
 * 包    名：com.api.wechat.connect.socket.bean
 * 描    述：消息类型
 * Create by zFox from IntelliJ-2019.5
 * ================================================
 */
public class MessageType {
    /**
     * 系统消息推送
     */
    public final static int MESSAGE_SYSTEM_PUSH = -1;
    /**
     * 用户单聊消息
     */
    public final static int MESSAGE_USER_USER = 0;
    /**
     * 事件推送
     */
    public final static int MESSAGE_EVENT_PUSH = 1;
    /**
     * 状态推送
     */
    public final static int MESSAGE_STATUS_PUSH = 2;
    /**
     * 群组聊天
     */
    public final static int MESSAGE_USER_GROUP = 3;

    public final static int MESSAGE_USER_INFO = 999;
    public final static int MESSAGE_USER_LOGOUT = 333;
}
