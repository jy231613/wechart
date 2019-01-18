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
 * 描    述：
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
@Table("msg_list")
public class UserMsgListDb {

    @Override
    public String toString() {
        return "UserMsgListDb{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userImage='" + userImage + '\'' +
                ", userId=" + userId +
                ", noSelect=" + noSelect +
                ", lastMsg='" + lastMsg + '\'' +
                ", lastDate='" + lastDate + '\'' +
                ", forbidden=" + forbidden +
                '}';
    }

    @SystemId
    public int id;

    @Param
    public String userName;//用户名称
    @Param
    public String userImage;//用户图像地址
    @Param
    public int userId;//用户id
    @Param
    public int noSelect;//未读条数
    @Param
    public String lastMsg;//最后一条信息
    @Param
    public String lastDate;//最后聊天时间
    @Param
    public int forbidden;//0未禁用,1禁用

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNoSelect() {
        return noSelect;
    }

    public void setNoSelect(int noSelect) {
        this.noSelect = noSelect;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public int getForbidden() {
        return forbidden;
    }

    public void setForbidden(int forbidden) {
        this.forbidden = forbidden;
    }
}
