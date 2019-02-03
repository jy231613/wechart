package com.qb.wxbase.coze.model;

import com.qb.wxbase.coze.bean.SocketHeader;
import com.qb.wxbase.coze.bean.SocketRelation;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/19
 * 包    名：com.api.wechat.connect.socket
 * 描    述：Socket传输对象
 * Create by zFox from IntelliJ-2019.5
 * ================================================
 */
public class SocketTransmit {
    private SocketHeader socketHeader;//socket头信息
    private int socketType;//传输类型//传输类型:0--消息类信息,1--事件类信息,2--状态类信息,999--个人信息,333--退出信息
    private SocketRelation socketRelation;//消息关系对象
    private String message;//消息对象json体

    public SocketHeader getSocketHeader() {
        return socketHeader;
    }

    public void setSocketHeader(SocketHeader socketHeader) {
        this.socketHeader = socketHeader;
    }

    public int getSocketType() {
        return socketType;
    }

    public void setSocketType(int socketType) {
        this.socketType = socketType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SocketRelation getSocketRelation() {
        return socketRelation;
    }

    public void setSocketRelation(SocketRelation socketRelation) {
        this.socketRelation = socketRelation;
    }
}
