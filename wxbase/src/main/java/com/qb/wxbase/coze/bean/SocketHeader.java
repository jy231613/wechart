package com.qb.wxbase.coze.bean;

import java.util.Date;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/19
 * 包    名：com.api.wechat.connect.socket.bean
 * 描    述：Socket消息头
 * Create by zFox from IntelliJ-2019.5
 * ================================================
 */
public class SocketHeader {
    private String requestIp;//请求ip
    private String userId;//请求id
    private String requestKey;//请求key
    private String phoneName;//请求手机名称
    private Date sendTime;//消息发送时间

    public String getPhoneName() {
        return phoneName;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
    }
}
