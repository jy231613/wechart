package com.qb.wechat.aax;

import com.qb.wxbase.create.sql.note.Param;
import com.qb.wxbase.create.sql.note.SystemId;
import com.qb.wxbase.create.sql.note.Table;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/17
 * 包    名：com.qb.wechat.aax
 * 描    述：用户聊天信息--每一条
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
@Table("user_msg")
public class UserMsgDb {

    @SystemId
    public int id;//信息id

    @Param
    public int userId;//用户id
    @Param
    public String myId;//自身id
    @Param
    public String msg;//用户消息
    @Param
    public int msgType;//消息类型,0文本,1图片
    @Param
    public int mySend;//0自己发的,1对方发的
    @Param
    public int inSelect;//0未查看,1已查看
    @Param
    public String msgDate;//消息发送时间

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    public int getInSelect() {
        return inSelect;
    }

    public void setInSelect(int inSelect) {
        this.inSelect = inSelect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getMySend() {
        return mySend;
    }

    public void setMySend(int mySend) {
        this.mySend = mySend;
    }

}
