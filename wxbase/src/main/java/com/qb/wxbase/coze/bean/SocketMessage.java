package com.qb.wxbase.coze.bean;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/19
 * 包    名：com.api.wechat.connect.socket.bean
 * 描    述：socket消息类
 * Create by zFox from IntelliJ-2019.5
 * ================================================
 */
public class SocketMessage {
    private int messageType;//消息类型
    private String message;//消息内容
    private String messageUrl;//附件消息连接地址
    private String messageTime;//消息发送时间
    private String remake;//消息备注

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }
}
