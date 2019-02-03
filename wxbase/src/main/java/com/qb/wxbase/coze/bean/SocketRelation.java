package com.qb.wxbase.coze.bean;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/25
 * 包    名：com.api.wechat.connect.socket.bean
 * 描    述：Socket关系
 * Create by zFox from IntelliJ-2019.5
 * ================================================
 */
public class SocketRelation {
    private int inId;
    private int toId;
    private int relationId;

    public int getInId() {
        return inId;
    }

    public void setInId(int inId) {
        this.inId = inId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }
}
